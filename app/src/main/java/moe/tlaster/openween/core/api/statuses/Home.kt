package moe.tlaster.openween.core.api.statuses

import java.util.HashMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.status.MessageListModel
import moe.tlaster.openween.core.model.types.FeatureType
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback

/**
 * Created by Tlaster on 2016/9/7.
 */
object Home {
    fun getTimeline(count: Int = 20, page: Int = 1, max_id: Long = 0, since_id: Long = 0, base_app: Int = 0, feature: FeatureType = FeatureType.All, trim_user: Int = 0, callback: JsonCallback<MessageListModel>) {
        val param = HashMap<String, String>()
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        param.put("base_app", base_app.toString())
        param.put("trim_user", trim_user.toString())
        param.put("feature", feature.value.toString())
        HttpHelper.getAsync(Constants.HOME_TIMELINE, param, callback)
    }
}
