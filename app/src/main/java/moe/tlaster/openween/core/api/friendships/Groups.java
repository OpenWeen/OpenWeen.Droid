package moe.tlaster.openween.core.api.friendships;

import java.util.HashMap;
import java.util.Map;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.status.GroupListModel;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.core.model.status.MessageListModel;
import moe.tlaster.openween.core.model.types.FeatureType;

/**
 * Created by Tlaster on 2016/9/7.
 */
public class Groups {
    public static void getGroups(JsonCallback<GroupListModel> callback) {
        HttpHelper.getAsync(Constants.FRIENDSHIPS_GROUPS, null, callback);
    }
    public static void getGroupTimeline(String list_id, long max_id, int count, JsonCallback<MessageListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(1));
        param.put("since_id", String.valueOf(0));
        param.put("max_id", String.valueOf(max_id));
        param.put("list_id", list_id);
        param.put("feature", String.valueOf(FeatureType.All.getValue()));
        HttpHelper.getAsync(Constants.FRIENDSHIPS_GROUPS_TIMELINE, param, callback);
    }
    public static void getGroupTimeline(String list_id, int count, JsonCallback<MessageListModel> callback) {
        getGroupTimeline(list_id, 0, count, callback);
    }
}
