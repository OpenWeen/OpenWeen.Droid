package moe.tlaster.openween.core.api.user;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.core.model.LimitStatusModel;

/**
 * Created by Tlaster on 2016/9/9.
 */
public class Account {
    public static void getUid(JsonCallback<String> callback) {
        HttpHelper.getAsync(Constants.GET_UID, null, callback);
    }
    public static void getUid(String token, JsonCallback<String> callback) {
        HttpHelper.getAsync(Constants.GET_UID, token, null, callback);
    }
    public static void getLimitStatus(JsonCallback<LimitStatusModel> callback) {
        HttpHelper.getAsync(Constants.RATE_LIMIT_STATUS, null, callback);
    }
}
