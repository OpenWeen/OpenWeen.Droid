package moe.tlaster.openween.core.api

import android.text.TextUtils

import moe.tlaster.openween.App
import moe.tlaster.openween.common.helpers.SettingHelper

/**
 * Created by Tlaster on 2016/9/3.
 */
object Entity {
    //return SettingHelper.getListSetting(App.getContext(), SettingHelper.ACCESSTOKEN)[0];
    var accessToken: String? = null

    //public static void setAccessToken(String mAccessToken) {
    //    Entity.mAccessToken = mAccessToken;
    //}
    fun getOauthLoginPage(appid: String, appSecret: String, redirectUri: String, packageName: String, scope: String): String {
        return Constants.OAUTH2_ACCESS_AUTHORIZE + "client_id=" + appid + "&response_type=token&redirect_uri=" + redirectUri + "&key_hash=" + appSecret + (if (TextUtils.isEmpty(packageName)) "" else "&packagename=" + packageName) + "&display=mobile&scope=" + scope
    }
}
