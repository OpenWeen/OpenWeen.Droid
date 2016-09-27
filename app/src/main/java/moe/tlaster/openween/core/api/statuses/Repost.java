package moe.tlaster.openween.core.api.statuses;

import java.util.HashMap;
import java.util.Map;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.status.RepostListModel;
import moe.tlaster.openween.core.model.types.AuthorType;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/9.
 */
public class Repost {
    public static void getRepost(long id, long since_id, long max_id, int count, int page, AuthorType filter_by_author, JsonCallback<RepostListModel> callback) throws InvalidAccessTokenException {
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        param.put("since_id", String.valueOf(since_id));
        param.put("max_id", String.valueOf(max_id));
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        param.put("filter_by_author", String.valueOf(filter_by_author.getValue()));
        HttpHelper.getAsync(Constants.REPOST_TIMELINE, param, callback);
    }
    public static void getRepost(long id, int count, int page, JsonCallback<RepostListModel> callback) throws InvalidAccessTokenException {
        getRepost(id, 0, 0, count, page, AuthorType.All, callback);
    }
}
