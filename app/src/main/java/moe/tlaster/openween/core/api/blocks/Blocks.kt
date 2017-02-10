package moe.tlaster.openween.core.api.blocks

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.android.core.Json
import android.support.v4.util.ArrayMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.block.BlockListModel
import moe.tlaster.openween.core.model.user.UserModel
import moe.tlaster.openween.common.helpers.HttpHelper
import org.json.JSONObject

/**
 * Created by Tlaster on 2016/9/5.
 */
object Blocks {
    fun getBlocksList(count: Int = 50, page: Int = 1) : BlockListModel {
        val param = ArrayMap<String, String>()
        param.put("count", count.toString())
        param.put("page", page.toString())
        return HttpHelper.getAsync(Constants.BLOCKS_LIST, param)
    }

    fun addBlock(uid: Long) : UserModel {
        val param = ArrayMap<String, String>()
        param.put("uid", uid.toString())
        return HttpHelper.postAsync(Constants.BLOCKS_CREATE, param)
    }

    fun removeBlock(uid: Long) : UserModel {
        val param = ArrayMap<String, String>()
        param.put("uid", uid.toString())
        return HttpHelper.postAsync(Constants.BLOCKS_DESTROY, param)
    }

    fun isBlocked(uid: Long, invert: Boolean = false) : Boolean {
        val param = ArrayMap<String, String>()
        param.put("uid", uid.toString())
        param.put("invert", if (invert) "0" else "1")
        param.put("source", "211160679")
        param.put("from", "1055095010")
        return Json(HttpHelper.getAsync(Constants.BLOCKS_EXISTS, param)).obj().optBoolean("result")
    }

}
