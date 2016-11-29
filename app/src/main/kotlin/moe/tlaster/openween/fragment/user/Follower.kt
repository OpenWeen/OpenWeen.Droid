package moe.tlaster.openween.fragment.user

import android.os.Bundle

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.typeface.IIcon

import moe.tlaster.openween.adapter.UserListAdapter
import moe.tlaster.openween.common.StaticResource
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.api.friendships.Friends
import moe.tlaster.openween.core.api.remind.Remind
import moe.tlaster.openween.core.model.types.RemindType
import moe.tlaster.openween.core.model.user.UserListModel
import moe.tlaster.openween.core.model.user.UserModel
import moe.tlaster.openween.fragment.WeiboListBase
import okhttp3.Call

/**
 * Created by Asahi on 2016/11/3.
 */

class Follower : WeiboListBase<UserModel>() {

    private var mID: Long = 0
    private var mCursor: Int = 0

    override fun initAdapter(): BaseQuickAdapter<UserModel, BaseViewHolder> {
        return UserListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mID = arguments.getLong("id")
    }

    override fun loadMoreOverride(callback: WeiboListBase.Callback<List<UserModel>>) {
        Friends.getFollowers(mID, mLoadCount, mCursor, object : WeiboListCallback<UserListModel>() {
            override fun onResponse(response: UserListModel, id: Int) {
                mCursor = Integer.parseInt(response.nextCursor)
                callback.onResponse(response.users!!, response.totalNumber)
            }
        })
    }

    override fun refreshOverride(callback: WeiboListBase.Callback<List<UserModel>>) {
        if (mID == StaticResource.uid)
            Remind.clearUnread(RemindType.Follower)
        mCursor = 0
        Friends.getFollowers(mID, mLoadCount, mCursor, object : WeiboListCallback<UserListModel>() {
            override fun onResponse(response: UserListModel, id: Int) {
                mCursor = Integer.parseInt(response.nextCursor)
                callback.onResponse(response.users!!, response.totalNumber)
            }
        })
    }

    override val icon: IIcon
        get() = GoogleMaterial.Icon.gmd_people

    companion object {
        fun create(id: Long): Follower {
            val follower = Follower()
            val bundle = Bundle()
            bundle.putLong("id", id)
            follower.arguments = bundle
            return follower
        }
    }
}