package moe.tlaster.openween.core.api.friendships

import java.util.HashMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.status.GroupListModel
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.model.status.MessageListModel
import moe.tlaster.openween.core.model.types.FeatureType

/**
 * Created by Tlaster on 2016/9/7.
 */
object Groups {
    fun getGroups(callback: JsonCallback<GroupListModel>) {
        HttpHelper.getAsync(Constants.FRIENDSHIPS_GROUPS, null, callback)
    }

    fun getGroupTimeline(list_id: String, max_id: Long = 0, count: Int = 20, callback: JsonCallback<MessageListModel>) {
        val param = HashMap<String, String>()
        param.put("count", count.toString())
        param.put("page", 1.toString())
        param.put("since_id", 0.toString())
        param.put("max_id", max_id.toString())
        param.put("list_id", list_id)
        param.put("feature", FeatureType.All.value.toString())
        HttpHelper.getAsync(Constants.FRIENDSHIPS_GROUPS_TIMELINE, param, callback)
    }
}
