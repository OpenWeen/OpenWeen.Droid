package moe.tlaster.openween.core.api.friendships;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.status.GroupListModel;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/7.
 */
public class Groups {
    public static void getGroups(JsonCallback<GroupListModel> callback) throws InvalidAccessTokenException {
        HttpHelper.getAsync(Constants.FRIENDSHIPS_GROUPS, null, callback);
    }
}
