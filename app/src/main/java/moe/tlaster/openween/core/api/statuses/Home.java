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
 * Created by Tlaster on 2016/9/7.
 */
public class Home {
    public static void getTimeline(int count, int page, long max_id, long since_id, int base_app, FeatureType feature, int trim_user, JsonCallback<MessageListModel> callback) throws InvalidAccessTokenException {
        Map<String, String> param = new HashMap<>();
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        param.put("since_id", String.valueOf(since_id));
        param.put("max_id", String.valueOf(max_id));
        param.put("base_app", String.valueOf(base_app));
        param.put("trim_user", String.valueOf(trim_user));
        param.put("feature", String.valueOf(feature.getValue()));
        HttpHelper.getAsync(Constants.HOME_TIMELINE, param, callback);
    }
    public static void getTimeline(int count, JsonCallback<MessageListModel> callback) throws InvalidAccessTokenException {
        getTimeline(count, 1, 0, 0, 0, FeatureType.All, 0, callback);
    }
    public static void getTimeline(int count, long max_id, JsonCallback<MessageListModel> callback) throws InvalidAccessTokenException {
        getTimeline(count, 1, max_id, 0, 0, FeatureType.All, 0, callback);
    }
}
