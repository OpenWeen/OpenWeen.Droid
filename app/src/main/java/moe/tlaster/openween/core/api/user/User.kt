package moe.tlaster.openween.core.api.user

import android.support.v4.util.ArrayMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.user.UserModel
import moe.tlaster.openween.common.helpers.HttpHelper

/**
 * Created by Tlaster on 2016/9/9.
 */
object User {
    fun getUser(uid: Long) : UserModel {
        val param = ArrayMap<String, String>()
        param.put("uid", uid.toString())
        return HttpHelper.getAsync(Constants.USER_SHOW, param)
    }

    fun getUser(screen_name: String) : UserModel {
        val param = ArrayMap<String, String>()
        param.put("screen_name", screen_name)
        return HttpHelper.getAsync(Constants.USER_SHOW, param)
    }
}
