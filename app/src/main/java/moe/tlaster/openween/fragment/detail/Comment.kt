package moe.tlaster.openween.fragment.detail

import android.os.Bundle

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.typeface.IIcon

import moe.tlaster.openween.adapter.BaseModelAdapter
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.api.comments.Comments
import moe.tlaster.openween.core.model.comment.CommentListModel
import moe.tlaster.openween.core.model.comment.CommentModel
import moe.tlaster.openween.core.model.types.AuthorType
import moe.tlaster.openween.fragment.WeiboListBase
import okhttp3.Call

/**
 * Created by Asahi on 2016/10/29.
 */

class Comment : WeiboListBase<CommentModel>() {

    private var mID: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mID = arguments.getLong("id")
    }

    override fun initAdapter(): BaseQuickAdapter<CommentModel, BaseViewHolder> {
        return BaseModelAdapter(false)
    }

    override fun loadMoreOverride(callback: WeiboListBase.Callback<List<CommentModel>>) {
        Comments.getCommentStatus(mID, max_id = (mAdapter!!.data[mAdapter!!.data.size - 1] as CommentModel).id, count =  mLoadCount, callback =  object : JsonCallback<CommentListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                callback.onError(e)
            }

            override fun onResponse(response: CommentListModel, id: Int) {
                response.comments = response.comments?.subList(1, response.comments?.lastIndex!!)
                callback.onResponse(response.comments!!, response.totalNumber)
            }
        })
    }

    override fun refreshOverride(callback: WeiboListBase.Callback<List<CommentModel>>) {
        Comments.getCommentStatus(mID, count =  mLoadCount, callback =  object : JsonCallback<CommentListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                callback.onError(e)
            }

            override fun onResponse(response: CommentListModel, id: Int) {
                callback.onResponse(response.comments!!, response.totalNumber)
            }
        })
    }

    override val icon: IIcon
        get() = GoogleMaterial.Icon.gmd_comment

    companion object {

        fun create(id: Long): Comment {
            val comment = Comment()
            val bundle = Bundle()
            bundle.putLong("id", id)
            comment.arguments = bundle
            return comment
        }
    }
}
