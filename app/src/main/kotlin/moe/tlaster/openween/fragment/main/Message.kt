package moe.tlaster.openween.fragment.main

import android.support.v7.widget.RecyclerView
import android.view.View

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.typeface.IIcon

import java.util.ArrayList
import com.chad.library.adapter.base.BaseViewHolder

import moe.tlaster.openween.adapter.BaseModelAdapter
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.api.comments.Comments
import moe.tlaster.openween.core.api.remind.Remind
import moe.tlaster.openween.core.api.statuses.Mentions
import moe.tlaster.openween.core.model.BaseModel
import moe.tlaster.openween.core.model.comment.CommentListModel
import moe.tlaster.openween.core.model.status.MessageListModel
import moe.tlaster.openween.core.model.status.MessageModel
import moe.tlaster.openween.core.model.types.RemindType
import moe.tlaster.openween.fragment.WeiboListBase
import okhttp3.Call

/**
 * Created by Tlaster on 2016/9/9.
 */
class Message : WeiboListBase<BaseModel>() {

    private var mMentionID: Long = 0
    private var mCommentID: Long = 0
    private var mCommentMentionsID: Long = 0

    override fun initAdapter(): BaseQuickAdapter<BaseModel, BaseViewHolder> {
        return BaseModelAdapter()
    }
    override fun refreshOverride(callback: WeiboListBase.Callback<List<BaseModel>>) {
        val mention = ArrayList<BaseModel>()
        val mentionNumber = intArrayOf(0)
        val comment = ArrayList<BaseModel>()
        val commentNumber = intArrayOf(0)
        val commentMentions = ArrayList<BaseModel>()
        val commentMentionsNumber = intArrayOf(0)
        Remind.clearUnread(RemindType.MentionStatus)
        Mentions.getMentions(count = mLoadCount, callback = object : WeiboListCallback<MessageListModel>() {
            override fun onResponse(response: MessageListModel, id: Int) {
                mention.addAll(response.statuses!!)
                mentionNumber[0] += response.totalNumber
                if (response.statuses!!.isNotEmpty())
                    mMentionID = response.statuses!![response.statuses!!.size - 1].id
                Remind.clearUnread(RemindType.Comment)
                Comments.getCommentToMe(count = mLoadCount, callBack = object : JsonCallback<CommentListModel>() {
                    override fun onError(call: Call, e: Exception, id: Int) {
                        callback.onError(e)
                    }

                    override fun onResponse(response: CommentListModel, id: Int) {
                        comment.addAll(response.comments!!)
                        commentNumber[0] = response.totalNumber
                        if (response.comments!!.isNotEmpty())
                            mCommentID = response.comments!![response.comments!!.size - 1].id
                        Remind.clearUnread(RemindType.MentionComment)
                        Comments.getCommentMentions(count = mLoadCount, callback =  object : JsonCallback<CommentListModel>() {
                            override fun onError(call: Call, e: Exception, id: Int) {
                                callback.onError(e)
                            }

                            override fun onResponse(response: CommentListModel, id: Int) {
                                commentMentions.addAll(response.comments!!)
                                commentMentionsNumber[0] = response.totalNumber
                                if (response.comments!!.isNotEmpty())
                                    mCommentMentionsID = response.comments!![response.comments!!.size - 1].id
                                callback.onResponse((mention + comment + commentMentions).sortedByDescending { it.createdDate }, mentionNumber[0] + commentNumber[0] + commentMentionsNumber[0])
                            }
                        })
                    }
                })
            }
        })

    }

    override fun loadMoreOverride(callback: WeiboListBase.Callback<List<BaseModel>>) {

        val mention = ArrayList<BaseModel>()
        val mentionNumber = intArrayOf(0)
        val comment = ArrayList<BaseModel>()
        val commentNumber = intArrayOf(0)
        val commentMentions = ArrayList<BaseModel>()
        val commentMentionsNumber = intArrayOf(0)
        Mentions.getMentions(max_id = mMentionID, count =  mLoadCount, callback =  object : WeiboListCallback<MessageListModel>() {

            override fun onResponse(response: MessageListModel, id: Int) {
                mention.addAll(response.statuses!!)
                mentionNumber[0] += response.totalNumber
                if (response.statuses!!.isNotEmpty())
                    mMentionID = response.statuses?.last()?.id!!
                Comments.getCommentToMe(max_id = mCommentID, count =  mLoadCount, callBack =  object : JsonCallback<CommentListModel>() {
                    override fun onError(call: Call, e: Exception, id: Int) {
                        callback.onError(e)
                    }

                    override fun onResponse(response: CommentListModel, id: Int) {
                        comment.addAll(response.comments!!)
                        commentNumber[0] = response.totalNumber
                        if (response.comments!!.isNotEmpty())
                            mCommentID = response.comments?.last()?.id!!
                        Comments.getCommentMentions(max_id = mCommentMentionsID, count =  mLoadCount, callback =  object : JsonCallback<CommentListModel>() {
                            override fun onError(call: Call, e: Exception, id: Int) {
                                callback.onError(e)
                            }

                            override fun onResponse(response: CommentListModel, id: Int) {
                                commentMentions.addAll(response.comments!!)
                                commentMentionsNumber[0] = response.totalNumber
                                if (response.comments!!.isNotEmpty())
                                    mCommentMentionsID = response.comments?.last()?.id!!
                                callback.onResponse((mention + comment + commentMentions).sortedByDescending { it.createdDate }, mentionNumber[0] + commentNumber[0] + commentMentionsNumber[0])

                            }
                        })
                    }
                })
            }
        })
    }

    override val icon: IIcon
        get() = GoogleMaterial.Icon.gmd_forum
}
