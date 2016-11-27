package moe.tlaster.openween.core.api.statuses

import java.util.HashMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.status.MessageListModel
import moe.tlaster.openween.core.model.types.FeatureType
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback

/**
 * Created by Tlaster on 2016/9/9.
 */
object UserTimeline {
    fun getUserTimeline(uid: Long, count: Int = 20, page: Int = 1, max_id: Long = 0, since_id: Long = 0, base_app: Int = 0, feature: FeatureType = FeatureType.All, trim_user: Int = 0, callback: JsonCallback<MessageListModel>) {
        val param = HashMap<String, String>()
        param.put("uid", uid.toString())
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("base_app", base_app.toString())
        param.put("feature", feature.value.toString())
        param.put("trim_user", trim_user.toString())
        param.put("source", "211160679")
        param.put("from", "1055095010")
        HttpHelper.getAsync("https://api.weibo.cn/2/statuses/user_timeline", param, callback)
    }

    fun getUserTimeline(screen_name: String, count: Int = 20, page: Int = 1, max_id: Long = 0, since_id: Long = 0, base_app: Int = 0, feature: FeatureType = FeatureType.All, trim_user: Int = 0, callback: JsonCallback<MessageListModel>) {
        val param = HashMap<String, String>()
        param.put("screen_name", screen_name.toString())
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("base_app", base_app.toString())
        param.put("feature", feature.value.toString())
        param.put("trim_user", trim_user.toString())
        param.put("source", "211160679")
        param.put("from", "1055095010")
        HttpHelper.getAsync("https://api.weibo.cn/2/statuses/user_timeline", param, callback)
    }
}
