package moe.tlaster.openween.core.api.user

import android.support.v4.util.ArrayMap
import com.github.kittinunf.fuel.android.core.Json
import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.core.model.LimitStatusModel

/**
 * Created by Tlaster on 2016/9/9.
 */
object Account {
    fun getUid() : String {
        return Json(HttpHelper.getAsync(Constants.GET_UID, ArrayMap())).obj().optString("uid")
    }

//    fun getUid(token: String) {
//        HttpHelper.getAsync(Constants.GET_UID, token, ArrayMap())
//    }

    fun getLimitStatus() : LimitStatusModel {
        return HttpHelper.getAsync(Constants.RATE_LIMIT_STATUS, ArrayMap())
    }
}
