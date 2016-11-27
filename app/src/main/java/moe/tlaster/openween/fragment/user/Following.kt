package moe.tlaster.openween.fragment.user

import android.os.Bundle

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.typeface.IIcon

import moe.tlaster.openween.adapter.UserListAdapter
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.api.friendships.Friends
import moe.tlaster.openween.core.model.user.UserListModel
import moe.tlaster.openween.core.model.user.UserModel
import moe.tlaster.openween.fragment.WeiboListBase
import okhttp3.Call

/**
 * Created by Asahi on 2016/11/3.
 */

class Following : WeiboListBase<UserModel>() {


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
        Friends.getFriends(mID, mLoadCount, mCursor, object : JsonCallback<UserListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                callback.onError(e)
            }

            override fun onResponse(response: UserListModel, id: Int) {
                mCursor = Integer.parseInt(response.nextCursor)
                callback.onResponse(response.users!!, response.totalNumber)
            }
        })
    }

    override fun refreshOverride(callback: WeiboListBase.Callback<List<UserModel>>) {
        mCursor = 0
        Friends.getFriends(mID, mLoadCount, mCursor, object : JsonCallback<UserListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                callback.onError(e)
            }

            override fun onResponse(response: UserListModel, id: Int) {
                mCursor = Integer.parseInt(response.nextCursor)
                callback.onResponse(response.users!!, response.totalNumber)
            }
        })
    }

    override val icon: IIcon
        get() = GoogleMaterial.Icon.gmd_people

    companion object {


        fun create(id: Long): Following {
            val following = Following()
            val bundle = Bundle()
            bundle.putLong("id", id)
            following.arguments = bundle
            return following
        }
    }
}
