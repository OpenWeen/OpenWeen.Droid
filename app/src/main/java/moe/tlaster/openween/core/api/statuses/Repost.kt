package moe.tlaster.openween.core.api.statuses

import java.util.HashMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.status.RepostListModel
import moe.tlaster.openween.core.model.types.AuthorType
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback

/**
 * Created by Tlaster on 2016/9/9.
 */
object Repost {
    fun getRepost(id: Long, since_id: Long = 0, max_id: Long = 0, count: Int = 20, page: Int = 1, filter_by_author: AuthorType = AuthorType.All, callback: JsonCallback<RepostListModel>) {
        val param = HashMap<String, String>()
        param.put("id", id.toString())
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("filter_by_author", filter_by_author.value.toString())
        HttpHelper.getAsync(Constants.REPOST_TIMELINE, param, callback)
    }
}
