package moe.tlaster.openween.core.api.statuses

import android.support.v4.util.ArrayMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.status.MessageModel
import moe.tlaster.openween.common.helpers.HttpHelper


/**
 * Created by Tlaster on 2016/9/8.
 */
object Query {
    fun getStatus(id: Long, isGetLongText: Boolean) : MessageModel {
        val param = ArrayMap<String, String>()
        param.put("id", id.toString())
        param.put("isGetLongText", if (isGetLongText) "1" else "0")
        return HttpHelper.getAsync(Constants.SHOW, param)
    }
}
