package moe.tlaster.openween.core.api.remind;

import java.util.HashMap;
import java.util.Map;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.UnreadModel;
import moe.tlaster.openween.core.model.types.RemindType;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/7.
 */
public class Remind {
    public static void getUnread(String uid, JsonCallback<UnreadModel> callback) throws InvalidAccessTokenException {
        Map<String, String> param = new HashMap<>();
        param.put("uid", String.valueOf(uid));
        param.put("unread_message", "0");
        HttpHelper.getAsync(Constants.REMIND_UNREAD_COUNT, param, callback);
    }
    public static void clearUnread(RemindType type) throws InvalidAccessTokenException {
        Map<String, String> param = new HashMap<>();
        param.put("type", type.toString());
        HttpHelper.postAsync(Constants.REMIND_UNREAD_SET_COUNT, param, null);
    }
}
