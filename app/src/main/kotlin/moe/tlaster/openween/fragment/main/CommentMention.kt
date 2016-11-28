package moe.tlaster.openween.fragment.main

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.typeface.IIcon
import moe.tlaster.openween.adapter.BaseModelAdapter
import moe.tlaster.openween.core.api.comments.Comments
import moe.tlaster.openween.core.api.remind.Remind
import moe.tlaster.openween.core.model.comment.CommentListModel
import moe.tlaster.openween.core.model.comment.CommentModel
import moe.tlaster.openween.core.model.types.RemindType
import moe.tlaster.openween.fragment.WeiboListBase

/**
 * Created by Asahi on 16/11/28.
 */
class CommentMention(override val icon: IIcon = GoogleMaterial.Icon.gmd_comment) : WeiboListBase<CommentModel>() {
    override fun loadMoreOverride(callback: Callback<List<CommentModel>>) {
        Comments.getCommentMentions(max_id = mAdapter?.data?.last()?.id!!, count = mLoadCount, callback = object : WeiboListCallback<CommentListModel>() {
            override fun onResponse(response: CommentListModel?, id: Int) {
                response?.comments = response?.comments?.subList(1, response.comments?.lastIndex!!)
                callback.onResponse(response?.comments!!, response?.totalNumber!!)
            }
        })
    }

    override fun refreshOverride(callback: Callback<List<CommentModel>>) {
        Remind.clearUnread(RemindType.MentionComment)
        Comments.getCommentMentions(count = mLoadCount, callback = object : WeiboListCallback<CommentListModel>() {
            override fun onResponse(response: CommentListModel?, id: Int) {
                callback.onResponse(response?.comments!!, response?.totalNumber!!)
            }
        })
    }

    override fun initAdapter(): BaseQuickAdapter<CommentModel, BaseViewHolder> {
        return BaseModelAdapter()
    }
}