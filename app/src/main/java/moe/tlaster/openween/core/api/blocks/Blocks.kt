package moe.tlaster.openween.core.api.blocks

import java.util.HashMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.block.BlockListModel
import moe.tlaster.openween.core.model.user.UserModel
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback

/**
 * Created by Tlaster on 2016/9/5.
 */
object Blocks {
    fun getBlocksList(count: Int = 50, page: Int = 1, callback: JsonCallback<BlockListModel>) {
        val param = HashMap<String, String>()
        param.put("count", count.toString())
        param.put("page", page.toString())
        HttpHelper.getAsync(Constants.BLOCKS_LIST, param, callback)
    }

    fun addBlock(uid: Long, callback: JsonCallback<UserModel>) {
        val param = HashMap<String, String>()
        param.put("uid", uid.toString())
        HttpHelper.postAsync(Constants.BLOCKS_CREATE, param, callback)
    }

    fun removeBlock(uid: Long, callback: JsonCallback<UserModel>) {
        val param = HashMap<String, String>()
        param.put("uid", uid.toString())
        HttpHelper.postAsync(Constants.BLOCKS_DESTROY, param, callback)
    }

    fun isBlocked(uid: Long, invert: Boolean = false, callback: JsonCallback<String>) {
        val param = HashMap<String, String>()
        param.put("uid", uid.toString())
        param.put("invert", if (invert) "0" else "1")
        HttpHelper.getAsync(Constants.BLOCKS_EXISTS, param, callback)
    }

}
