package moe.tlaster.openween.core.api;

import android.text.TextUtils;

/**
 * Created by Tlaster on 2016/9/3.
 */
public class Entity {

    private static String mAccessToken;

    public static String getAccessToken() {
        return mAccessToken;
    }

    public static void setAccessToken(String mAccessToken) {
        Entity.mAccessToken = mAccessToken;
    }
    public static String getOauthLoginPage(String appid, String appSecret, String redirectUri, String packageName, String scope){
        return Constants.OAUTH2_ACCESS_AUTHORIZE+"client_id="+appid+"&response_type=token&redirect_uri="+redirectUri+"&key_hash="+appSecret+(TextUtils.isEmpty(packageName) ? "" : "&packagename="+packageName)+"&display=mobile&scope="+scope;
    }
}
