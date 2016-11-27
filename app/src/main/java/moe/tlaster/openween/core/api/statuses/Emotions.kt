package moe.tlaster.openween.core.api.statuses

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.EmotionModel
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback

/**
 * Created by Tlaster on 2016/9/7.
 */
object Emotions {
    fun getEmotions(callback: JsonCallback<List<EmotionModel>>) {
        HttpHelper.getAsync(Constants.EMOTIONS, null, callback)
    }
}
