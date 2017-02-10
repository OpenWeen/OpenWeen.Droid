package moe.tlaster.openween.core.api.friendships

import android.support.v4.util.ArrayMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.status.GroupListModel
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.core.model.status.MessageListModel
import moe.tlaster.openween.core.model.types.FeatureType

/**
 * Created by Tlaster on 2016/9/7.
 */
object Groups {
    fun getGroups() : GroupListModel {
        return HttpHelper.getAsync(Constants.FRIENDSHIPS_GROUPS, ArrayMap<String, String>())
    }

    fun getGroupTimeline(list_id: String, max_id: Long = 0, count: Int = 20) : MessageListModel {
        val param = ArrayMap<String, String>()
        param.put("count", count.toString())
        param.put("page", 1.toString())
        param.put("since_id", 0.toString())
        param.put("max_id", max_id.toString())
        param.put("list_id", list_id)
        param.put("feature", FeatureType.All.value.toString())
        return HttpHelper.getAsync(Constants.FRIENDSHIPS_GROUPS_TIMELINE, param)
    }
}
