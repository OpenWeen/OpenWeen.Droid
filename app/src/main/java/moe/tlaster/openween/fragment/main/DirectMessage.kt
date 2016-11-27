package moe.tlaster.openween.fragment.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.typeface.IIcon

import moe.tlaster.openween.R
import moe.tlaster.openween.adapter.DirectMessageUserListAdapter
import moe.tlaster.openween.common.controls.Pivot
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.model.directmessage.DirectMessageUserListModel
import moe.tlaster.openween.core.model.directmessage.DirectMessageUserModel
import moe.tlaster.openween.fragment.WeiboListBase
import okhttp3.Call

/**
 * Created by Tlaster on 2016/9/9.
 */
class DirectMessage : WeiboListBase<DirectMessageUserModel>() {


    private var mCursor: Int = 0

    override fun initAdapter(): BaseQuickAdapter<DirectMessageUserModel, BaseViewHolder> {
        return DirectMessageUserListAdapter()
    }

    override fun loadMoreOverride(callback: WeiboListBase.Callback<List<DirectMessageUserModel>>) {
        moe.tlaster.openween.core.api.directMessage.DirectMessage.getUserList(mLoadCount, mCursor, object : JsonCallback<DirectMessageUserListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                callback.onError(e)
            }

            override fun onResponse(response: DirectMessageUserListModel, id: Int) {
                mCursor = Integer.parseInt(response.nextCursor)
                callback.onResponse(response.userList!!, response.totalNumber)
            }
        })
    }

    override fun refreshOverride(callback: WeiboListBase.Callback<List<DirectMessageUserModel>>) {
        mCursor = 0
        moe.tlaster.openween.core.api.directMessage.DirectMessage.getUserList(mLoadCount, mCursor, object : JsonCallback<DirectMessageUserListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                callback.onError(e)
            }

            override fun onResponse(response: DirectMessageUserListModel, id: Int) {
                mCursor = Integer.parseInt(response.nextCursor)
                callback.onResponse(response.userList!!, response.totalNumber)
            }
        })
    }


    override val icon: IIcon
        get() = GoogleMaterial.Icon.gmd_email
}
