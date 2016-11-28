package moe.tlaster.openween.core.api.user

import java.util.HashMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.user.UserModel
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback

/**
 * Created by Tlaster on 2016/9/9.
 */
object User {
    fun getUser(uid: Long, callback: JsonCallback<UserModel>) {
        val param = HashMap<String, String>()
        param.put("uid", uid.toString())
        HttpHelper.getAsync(Constants.USER_SHOW, param, callback)
    }

    fun getUser(screen_name: String, callback: JsonCallback<UserModel>) {
        val param = HashMap<String, String>()
        param.put("screen_name", screen_name)
        HttpHelper.getAsync(Constants.USER_SHOW, param, callback)
    }
}
