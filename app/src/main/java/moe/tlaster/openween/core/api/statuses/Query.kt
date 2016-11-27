package moe.tlaster.openween.core.api.statuses

import java.util.HashMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.status.MessageModel
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback

/**
 * Created by Tlaster on 2016/9/8.
 */
object Query {
    fun getStatus(id: Long, isGetLongText: Boolean, callback: JsonCallback<MessageModel>) {
        val param = HashMap<String, String>()
        param.put("id", id.toString())
        param.put("isGetLongText", if (isGetLongText) "1" else "0")
        HttpHelper.getAsync(Constants.SHOW, param, callback)
    }
}
