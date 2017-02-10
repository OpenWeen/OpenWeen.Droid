package moe.tlaster.openween.core.api.statuses

import android.support.v4.util.ArrayMap
import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.EmotionModel
import moe.tlaster.openween.common.helpers.HttpHelper


/**
 * Created by Tlaster on 2016/9/7.
 */
object Emotions {
    fun getEmotions() : List<EmotionModel> {
        return HttpHelper.getAsync(Constants.EMOTIONS, ArrayMap())
    }
}
