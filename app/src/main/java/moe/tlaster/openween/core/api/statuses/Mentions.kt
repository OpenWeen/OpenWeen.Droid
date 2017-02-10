package moe.tlaster.openween.core.api.statuses

import android.support.v4.util.ArrayMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.status.MessageListModel
import moe.tlaster.openween.core.model.types.AuthorType
import moe.tlaster.openween.core.model.types.FeatureType
import moe.tlaster.openween.core.model.types.SourceType
import moe.tlaster.openween.common.helpers.HttpHelper


/**
 * Created by Tlaster on 2016/9/7.
 */
object Mentions {
    fun getMentions(since_id: Long = 0, max_id: Long = 0, count: Int = 20, page: Int = 1, filter_by_author: AuthorType = AuthorType.All, filter_by_source: SourceType = SourceType.All, filter_by_type: FeatureType = FeatureType.All) : MessageListModel {
        val param = ArrayMap<String, String>()
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("filter_by_author", filter_by_author.value.toString())
        param.put("filter_by_source", filter_by_source.value.toString())
        param.put("filter_by_type", filter_by_type.value.toString())
        return HttpHelper.getAsync(Constants.MENTIONS, param)
    }

}
