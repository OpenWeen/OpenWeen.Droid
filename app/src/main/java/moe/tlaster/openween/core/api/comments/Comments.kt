package moe.tlaster.openween.core.api.comments

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import android.support.v4.util.ArrayMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.comment.CommentListModel
import moe.tlaster.openween.core.model.comment.CommentModel
import moe.tlaster.openween.core.model.status.MediaModel
import moe.tlaster.openween.core.model.types.AuthorType
import moe.tlaster.openween.core.model.types.SourceType
import moe.tlaster.openween.common.helpers.HttpHelper

/**
 * Created by Tlaster on 2016/9/5.
 */
object Comments {
    fun getCommentStatus(id: Long, since_id: Long = 0, max_id: Long = 0, count: Int = 20, page: Int = 1, filter_by_author: AuthorType = AuthorType.All) : CommentListModel {
        val param = ArrayMap<String, String>()
        param.put("id", id.toString())
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        param.put("filter_by_author", filter_by_author.value.toString())
        return HttpHelper.getAsync(Constants.COMMENTS_SHOW, param)
    }

    fun getCommentByMe(since_id: Long = 0, max_id: Long = 0, count: Int = 20, page: Int = 1, filter_by_source: SourceType = SourceType.All) : CommentListModel {
        val param = ArrayMap<String, String>()
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        param.put("filter_by_source", filter_by_source.value.toString())
        return HttpHelper.getAsync(Constants.COMMENTS_BY_ME, param)
    }

    fun getCommentToMe(since_id: Long = 0, max_id: Long = 0, count: Int = 20, page: Int = 1, filter_by_author: AuthorType = AuthorType.All, filter_by_source: SourceType = SourceType.All) : CommentListModel {
        val param = ArrayMap<String, String>()
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        param.put("filter_by_author", filter_by_author.value.toString())
        param.put("filter_by_source", filter_by_source.value.toString())
        return HttpHelper.getAsync(Constants.COMMENTS_TO_ME, param)
    }

    fun getComment(since_id: Long = 0, max_id: Long = 0, count: Int = 20, page: Int = 1, trim_user: Int = 0) : CommentListModel {
        val param = ArrayMap<String, String>()
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        param.put("trim_user", trim_user.toString())
        return HttpHelper.getAsync(Constants.COMMENTS_TIMELINE, param)
    }

    fun getCommentMentions(since_id: Long = 0, max_id: Long = 0, count: Int = 20, page: Int = 1, filter_by_author: AuthorType = AuthorType.All, filter_by_source: SourceType = SourceType.All) : CommentListModel {
        val param = ArrayMap<String, String>()
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        param.put("filter_by_author", filter_by_author.value.toString())
        param.put("filter_by_source", filter_by_source.value.toString())
        return HttpHelper.getAsync(Constants.COMMENTS_MENTIONS, param)
    }

    fun batch(vararg cids: Long) : List<CommentModel> {
        var cid = ""
        for (id in cids) {
            cid += "," + id.toString()
        }
        cid = cid.substring(1)
        val param = ArrayMap<String, String>()
        param.put("cids", cid)
        return HttpHelper.getAsync(Constants.COMMENTS_BATCH, param)
    }

    fun postComment(id: Long, comment: String, commentOri: Boolean = false) : CommentModel {
        val param = ArrayMap<String, String>()
        param.put("id", id.toString())
        param.put("comment", comment)
        param.put("comment_ori", if (commentOri) "1" else "0")
        return HttpHelper.postAsync(Constants.COMMENTS_CREATE, param)
    }

    fun postCommentWithPic(id: Long, comment: String, pid: String, commentOri: Boolean = false) : CommentModel {
        val param = ArrayMap<String, String>()
        param.put("id", id.toString())
        param.put("comment", comment)
        param.put("comment_ori", if (commentOri) "1" else "0")
        param.put("media", MediaModel(pid).toString())
        param.put("source", "211160679")
        param.put("from", "1055095010")
        return HttpHelper.postAsync("https://api.weibo.cn/2/comments/create", param)
    }

    fun reply(id: Long, cid: Long, comment: String, comment_ori: Boolean = false, without_mention: Boolean = false) {
        val param = ArrayMap<String, String>()
        param.put("id", id.toString())
        param.put("cid", cid.toString())
        param.put("comment", comment)
        param.put("comment_ori", if (comment_ori) "1" else "0")
        param.put("without_mention", if (without_mention) "1" else "0")
        return HttpHelper.postAsync(Constants.COMMENTS_REPLY, param)
    }

    fun replyWithPic(id: Long, cid: Long, comment: String, pid: String, comment_ori: Boolean = false, without_mention: Boolean = false) {
        val param = ArrayMap<String, String>()
        param.put("id", id.toString())
        param.put("cid", cid.toString())
        param.put("comment", comment)
        param.put("comment_ori", if (comment_ori) "1" else "0")
        param.put("without_mention", if (without_mention) "1" else "0")
        param.put("media", MediaModel(pid).toString())
        param.put("source", "211160679")
        param.put("from", "1055095010")
        return HttpHelper.postAsync("https://api.weibo.cn/2/comments/reply", param)
    }

    fun delete(cid: Long) : CommentModel{
        val param = ArrayMap<String, String>()
        param.put("cid", cid.toString())
        return HttpHelper.postAsync(Constants.COMMENTS_DESTROY, param)
    }

    fun deleteBatch(vararg cids: Long) : List<CommentModel> {
        var cid = ""
        for (id in cids) {
            cid += "," + id.toString()
        }
        cid = cid.substring(1)
        val param = ArrayMap<String, String>()
        param.put("cids", cid)
        return HttpHelper.postAsync(Constants.COMMENTS_DESTROY_BATCH, param)
    }
}
