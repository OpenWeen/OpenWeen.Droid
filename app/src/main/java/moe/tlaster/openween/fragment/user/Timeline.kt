package moe.tlaster.openween.fragment.user

import android.os.Bundle

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.typeface.IIcon

import moe.tlaster.openween.adapter.BaseModelAdapter
import moe.tlaster.openween.adapter.UserListAdapter
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.api.statuses.UserTimeline
import moe.tlaster.openween.core.model.status.MessageListModel
import moe.tlaster.openween.core.model.status.MessageModel
import moe.tlaster.openween.core.model.user.UserModel
import moe.tlaster.openween.fragment.WeiboListBase
import moe.tlaster.openween.fragment.detail.Repost
import okhttp3.Call

/**
 * Created by Asahi on 2016/11/3.
 */

class Timeline : WeiboListBase<MessageModel>() {

    private var mID: Long = 0

    override fun initAdapter(): BaseQuickAdapter<MessageModel, BaseViewHolder> {
        return BaseModelAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mID = arguments.getLong("id")
    }


    override fun loadMoreOverride(callback: WeiboListBase.Callback<List<MessageModel>>) {
        UserTimeline.getUserTimeline(mID, count = mLoadCount, max_id = (mAdapter!!.data[mAdapter!!.data.size - 1] as MessageModel).id, callback = object : JsonCallback<MessageListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                callback.onError(e)
            }

            override fun onResponse(response: MessageListModel, id: Int) {
                response.statuses = response.statuses!!.subList(1, response.statuses!!.lastIndex)
                callback.onResponse(response.statuses!!, response.totalNumber)
            }
        })
    }

    override fun refreshOverride(callback: WeiboListBase.Callback<List<MessageModel>>) {
        UserTimeline.getUserTimeline(mID, count = mLoadCount, callback = object : JsonCallback<MessageListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                callback.onError(e)
            }

            override fun onResponse(response: MessageListModel, id: Int) {
                callback.onResponse(response.statuses!!, response.totalNumber)
            }
        })
    }

    override val icon: IIcon
        get() = GoogleMaterial.Icon.gmd_home

    companion object {

        fun create(id: Long): Timeline {
            val timeline = Timeline()
            val bundle = Bundle()
            bundle.putLong("id", id)
            timeline.arguments = bundle
            return timeline
        }
    }
}
