package moe.tlaster.openween.core.api.statuses;

import java.util.HashMap;
import java.util.Map;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.status.MessageModel;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/8.
 */
public class Query {
    public static void getStatus(long id, boolean isGetLongText, JsonCallback<MessageModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        param.put("isGetLongText", isGetLongText ? "1" : "0");
        HttpHelper.getAsync(Constants.SHOW, param, callback);
    }
}
