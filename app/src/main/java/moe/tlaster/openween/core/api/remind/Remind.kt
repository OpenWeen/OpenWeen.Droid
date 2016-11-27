package moe.tlaster.openween.core.api.remind

import java.util.HashMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.UnreadModel
import moe.tlaster.openween.core.model.types.RemindType
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback

/**
 * Created by Tlaster on 2016/9/7.
 */
object Remind {
    fun getUnread(uid: String, callback: JsonCallback<UnreadModel>) {
        val param = HashMap<String, String>()
        param.put("uid", uid.toString())
        param.put("unread_message", "0")
        HttpHelper.getAsync(Constants.REMIND_UNREAD_COUNT, param, callback)
    }

    fun getUnread(callback: JsonCallback<UnreadModel>) {
        HttpHelper.getAsync(Constants.REMIND_UNREAD_COUNT, null, callback)
    }

    fun clearUnread(type: RemindType) {
        val param = HashMap<String, String>()
        param.put("type", type.toString())
        HttpHelper.postAsync(Constants.REMIND_UNREAD_SET_COUNT, param, null)
    }
}
