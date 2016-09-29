package moe.tlaster.openween.core.api.statuses;

import java.util.List;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.EmotionModel;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/7.
 */
public class Emotions {
    public static void getEmotions(JsonCallback<List<EmotionModel>> callback) {
        HttpHelper.getAsync(Constants.EMOTIONS, null, callback);
    }
}
