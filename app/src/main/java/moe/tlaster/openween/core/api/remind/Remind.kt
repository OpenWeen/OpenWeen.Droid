package moe.tlaster.openween.core.api.remind

import android.support.v4.util.ArrayMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.UnreadModel
import moe.tlaster.openween.core.model.types.RemindType
import moe.tlaster.openween.common.helpers.HttpHelper

/**
 * Created by Tlaster on 2016/9/7.
 */
object Remind {
    fun getUnread(unread_message: Boolean = true) : UnreadModel {
        val param = ArrayMap<String, String>()
        param.put("unread_message", if (unread_message) "1" else "0")
        return HttpHelper.getAsync(Constants.REMIND_UNREAD_COUNT, param)
    }

    fun clearUnread(type: RemindType) : String {
        val param = ArrayMap<String, String>()
        param.put("type", type.toString())
        return HttpHelper.postAsync(Constants.REMIND_UNREAD_SET_COUNT, param)
    }
}
