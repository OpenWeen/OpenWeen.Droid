package moe.tlaster.openween.core.api.user

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.model.LimitStatusModel

/**
 * Created by Tlaster on 2016/9/9.
 */
object Account {
    fun getUid(callback: JsonCallback<String>) {
        HttpHelper.getAsync(Constants.GET_UID, null, callback)
    }

    fun getUid(token: String, callback: JsonCallback<String>) {
        HttpHelper.getAsync(Constants.GET_UID, token, null, callback)
    }

    fun getLimitStatus(callback: JsonCallback<LimitStatusModel>) {
        HttpHelper.getAsync(Constants.RATE_LIMIT_STATUS, null, callback)
    }
}
