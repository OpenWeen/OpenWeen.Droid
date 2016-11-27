package moe.tlaster.openween.fragment.main

import android.support.v7.widget.RecyclerView
import android.view.View

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.typeface.IIcon

import moe.tlaster.openween.R
import moe.tlaster.openween.adapter.BaseModelAdapter
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.api.friendships.Groups
import moe.tlaster.openween.core.api.statuses.Home
import moe.tlaster.openween.core.model.status.MessageListModel
import moe.tlaster.openween.core.model.status.MessageModel
import moe.tlaster.openween.fragment.WeiboListBase
import okhttp3.Call

/**
 * Created by Tlaster on 2016/9/9.
 */
class Timeline : WeiboListBase<MessageModel>() {


    override fun initAdapter(): BaseQuickAdapter<MessageModel, BaseViewHolder> {
        return BaseModelAdapter()
    }
    private var mGroupID: Long = -1

    override fun loadMoreOverride(callback: WeiboListBase.Callback<List<MessageModel>>) {
        val jsonCallback = object : JsonCallback<MessageListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                callback.onError(e)
            }

            override fun onResponse(response: MessageListModel, id: Int) {
                response.statuses = response.statuses!!.subList(1, response.statuses!!.lastIndex)
                callback.onResponse(response.statuses!!, response.totalNumber)
            }
        }
        if (mGroupID != -1L) {
            Groups.getGroupTimeline(mGroupID.toString(), max_id = (mAdapter!!.data[mAdapter!!.data.size - 1] as MessageModel).id, count = mLoadCount, callback = jsonCallback)
        } else {
            Home.getTimeline(count = mLoadCount, max_id = (mAdapter!!.data[mAdapter!!.data.size - 1] as MessageModel).id, callback = jsonCallback)
        }
    }

    override fun refreshOverride(callback: WeiboListBase.Callback<List<MessageModel>>) {
        val jsonCallback = object : JsonCallback<MessageListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                callback.onError(e)
            }

            override fun onResponse(response: MessageListModel, id: Int) {
                callback.onResponse(response.statuses!!, response.totalNumber)
            }
        }
        if (mGroupID != -1L) {
            Groups.getGroupTimeline(mGroupID.toString(), count = mLoadCount, callback = jsonCallback)
        } else {
            Home.getTimeline(mLoadCount, callback = jsonCallback)
        }
    }

    override val icon: IIcon
        get() = GoogleMaterial.Icon.gmd_home

    fun toGroup(id: Long) {
        mGroupID = id
        refresh()
    }
}
