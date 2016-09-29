package moe.tlaster.openween.core.api.attitudes;

import java.util.HashMap;
import java.util.Map;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class Attitudes {
    public static void like(long id, JsonCallback<String> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("attitude", "smile");
        param.put("id", String.valueOf(id));
        HttpHelper.postAsync(Constants.ATTITUDE_CREATE, param, callback);
    }
    public static void unLike(long id, JsonCallback<String> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        HttpHelper.postAsync(Constants.ATTITUDE_DESTROY, param, callback);
    }
}
