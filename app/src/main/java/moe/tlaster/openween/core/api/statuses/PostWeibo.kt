package moe.tlaster.openween.core.api.statuses

import android.text.TextUtils

import java.io.File
import java.util.HashMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.status.MediaModel
import moe.tlaster.openween.core.model.status.MessageModel
import moe.tlaster.openween.core.model.status.PictureModel
import moe.tlaster.openween.core.model.types.RepostType
import moe.tlaster.openween.core.model.types.WeiboVisibility
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback

/**
 * Created by Tlaster on 2016/9/8.
 */
object PostWeibo {
    fun post(status: String, visible: WeiboVisibility = WeiboVisibility.All, list_id: String = "", plat: Float = 0f, plong: Float = 0f, callback: JsonCallback<MessageModel>) {
        var param: MutableMap<String, String> = HashMap()
        param.put("status", status)
        param.put("visible", visible.value.toString())
        param.put("lat", plat.toString())
        param.put("long", plong.toString())
        param = checkForVisibility(visible, list_id, param)
        HttpHelper.postAsync(Constants.UPDATE, param, callback)
    }

    fun postWithPic(status: String, pic: File, visible: WeiboVisibility, list_id: String, plat: Float, plong: Float, callback: JsonCallback<MessageModel>) {
        var param: MutableMap<String, String> = HashMap()
        param.put("status", status)
        param.put("visible", visible.value.toString())
        param.put("lat", plat.toString())
        param.put("long", plong.toString())
        param = checkForVisibility(visible, list_id, param)
        HttpHelper.uploadFileWithParamAsync(Constants.UPLOAD, pic, param, callback)
    }

    fun repost(id: Long, status: String, is_comment: RepostType = RepostType.None, callback: JsonCallback<MessageModel>) {
        val param = HashMap<String, String>()
        param.put("status", if (TextUtils.isEmpty(status)) "转发微博" else status)
        param.put("id", id.toString())
        param.put("is_comment", is_comment.value.toString())
        HttpHelper.postAsync(Constants.REPOST, param, callback)
    }

    fun repostWithPic(id: Long, status: String, pid: String, is_comment: RepostType = RepostType.None, callback: JsonCallback<MessageModel>) {
        val param = HashMap<String, String>()
        param.put("status", if (TextUtils.isEmpty(status)) "转发微博" else status)
        param.put("id", id.toString())
        param.put("is_comment", is_comment.value.toString())
        param.put("media", MediaModel(pid).toString())
        param.put("source", "211160679")
        param.put("from", "1055095010")
        HttpHelper.postAsync("https://api.weibo.cn/2/statuses/repost", param, callback)
    }

    fun deletePost(id: Long, callback: JsonCallback<MessageModel>) {
        val param = HashMap<String, String>()
        param.put("id", id.toString())
        HttpHelper.postAsync(Constants.DESTROY, param, callback)
    }

    fun uploadPicture(file: File, callback: JsonCallback<PictureModel>) {
        HttpHelper.uploadFileAsync(Constants.UPLOAD_PIC, file, callback)
    }

    fun postWithMultiPics(status: String, pics: String, visible: WeiboVisibility = WeiboVisibility.All, list_id: String = "", plat: Float = 0f, plong: Float = 0f, callback: JsonCallback<MessageModel>) {
        var param: MutableMap<String, String> = HashMap()
        param.put("status", status)
        param.put("pic_id", pics)
        param.put("visible", visible.value.toString())
        param.put("lat", plat.toString())
        param.put("long", plong.toString())
        param = checkForVisibility(visible, list_id, param)
        HttpHelper.postAsync(Constants.UPLOAD_URL_TEXT, param, callback)
    }

    private fun checkForVisibility(visible: WeiboVisibility, list_id: String, param: MutableMap<String, String>): MutableMap<String, String> {
        if (visible === WeiboVisibility.SpecifiedGroup)
            if (!TextUtils.isEmpty(list_id))
                param.put("list_id", list_id)
        return param
    }
}
