package moe.tlaster.openween.core.api.user;

import java.util.HashMap;
import java.util.Map;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.user.UserModel;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/9.
 */
public class User {
    public static void getUser(long uid, JsonCallback<UserModel> callback) throws InvalidAccessTokenException {
        Map<String, String> param = new HashMap<>();
        param.put("uid", String.valueOf(uid));
        HttpHelper.getAsync(Constants.USER_SHOW, param, callback);
    }
    public static void getUser(String screen_name, JsonCallback<UserModel> callback) throws InvalidAccessTokenException {
        Map<String, String> param = new HashMap<>();
        param.put("screen_name", screen_name);
        HttpHelper.getAsync(Constants.USER_SHOW, param, callback);
    }
}
