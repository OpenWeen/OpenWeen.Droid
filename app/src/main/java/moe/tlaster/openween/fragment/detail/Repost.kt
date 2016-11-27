package moe.tlaster.openween.fragment.detail

import android.os.Bundle

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.typeface.IIcon

import moe.tlaster.openween.adapter.BaseModelAdapter
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.model.status.MessageModel
import moe.tlaster.openween.core.model.status.RepostListModel
import moe.tlaster.openween.core.model.types.AuthorType
import moe.tlaster.openween.fragment.WeiboListBase
import okhttp3.Call

/**
 * Created by Asahi on 2016/10/29.
 */

class Repost : WeiboListBase<MessageModel>() {

    private var mID: Long = 0

    override fun initAdapter(): BaseQuickAdapter<MessageModel, BaseViewHolder> {
        return BaseModelAdapter(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mID = arguments.getLong("id")
    }

    override fun loadMoreOverride(callback: WeiboListBase.Callback<List<MessageModel>>) {
        moe.tlaster.openween.core.api.statuses.Repost.getRepost(mID, max_id = (mAdapter!!.data[mAdapter!!.data.size - 1] as MessageModel).id, count = mLoadCount, callback = object : JsonCallback<RepostListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                callback.onError(e)
            }

            override fun onResponse(response: RepostListModel, id: Int) {
                response.reposts = response.reposts!!.subList(1, response.reposts?.lastIndex!!)
                callback.onResponse(response.reposts!!, response.totalNumber)
            }
        })
    }

    override fun refreshOverride(callback: WeiboListBase.Callback<List<MessageModel>>) {
        moe.tlaster.openween.core.api.statuses.Repost.getRepost(mID, count =  mLoadCount, callback = object : JsonCallback<RepostListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                callback.onError(e)
            }

            override fun onResponse(response: RepostListModel, id: Int) {
                callback.onResponse(response.reposts!!, response.totalNumber)
            }
        })
    }

    override val icon: IIcon
        get() = GoogleMaterial.Icon.gmd_reply

    companion object {

        fun create(id: Long): Repost {
            val repost = Repost()
            val bundle = Bundle()
            bundle.putLong("id", id)
            repost.arguments = bundle
            return repost
        }
    }
}
