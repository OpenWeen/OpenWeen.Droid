package moe.tlaster.openween.core.api.statuses;

import java.util.HashMap;
import java.util.Map;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.status.MessageListModel;
import moe.tlaster.openween.core.model.types.FeatureType;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/9.
 */
public class UserTimeline {
    public static void getUserTimeline(long uid, int count, int page, long max_id, long since_id, int base_app, FeatureType feature, int trim_user, JsonCallback<MessageListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("uid", String.valueOf(uid));
        param.put("since_id", String.valueOf(since_id));
        param.put("max_id", String.valueOf(max_id));
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        param.put("base_app", String.valueOf(base_app));
        param.put("feature", String.valueOf(feature.getValue()));
        param.put("trim_user", String.valueOf(trim_user));
        HttpHelper.getAsync(Constants.USER_TIMELINE, param, callback);
    }
    public static void getUserTimeline(long uid, int count, int page, JsonCallback<MessageListModel> callback) {
        getUserTimeline(uid, count, page, 0, 0, 0, FeatureType.All, 0, callback);
    }


    public static void getUserTimeline(String screen_name, int count, int page, long max_id, long since_id, int base_app, FeatureType feature, int trim_user, JsonCallback<MessageListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("screen_name", String.valueOf(screen_name));
        param.put("since_id", String.valueOf(since_id));
        param.put("max_id", String.valueOf(max_id));
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        param.put("base_app", String.valueOf(base_app));
        param.put("feature", String.valueOf(feature.getValue()));
        param.put("trim_user", String.valueOf(trim_user));
        HttpHelper.getAsync(Constants.USER_TIMELINE, param, callback);
    }
    public static void getUserTimeline(String screen_name, int count, int page, JsonCallback<MessageListModel> callback) {
        getUserTimeline(screen_name, count, page, 0, 0, 0, FeatureType.All, 0, callback);
    }

}
