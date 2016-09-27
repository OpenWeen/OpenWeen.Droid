package moe.tlaster.openween.core.api.statuses;

import java.util.HashMap;
import java.util.Map;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.status.MessageListModel;
import moe.tlaster.openween.core.model.types.AuthorType;
import moe.tlaster.openween.core.model.types.FeatureType;
import moe.tlaster.openween.core.model.types.SourceType;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/7.
 */
public class Mentions {
    public static void getMentions(long since_id, long max_id, int count, int page, AuthorType filter_by_author, SourceType filter_by_source, FeatureType filter_by_type, JsonCallback<MessageListModel> callback) throws InvalidAccessTokenException {
        Map<String, String> param = new HashMap<>();
        param.put("since_id", String.valueOf(since_id));
        param.put("max_id", String.valueOf(max_id));
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        param.put("filter_by_author", String.valueOf(filter_by_author.getValue()));
        param.put("filter_by_source", String.valueOf(filter_by_source.getValue()));
        param.put("filter_by_type", String.valueOf(filter_by_type.getValue()));
        HttpHelper.getAsync(Constants.MENTIONS, param, callback);
    }

}
