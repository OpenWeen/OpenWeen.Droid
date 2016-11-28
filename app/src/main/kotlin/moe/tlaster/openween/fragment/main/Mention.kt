package moe.tlaster.openween.fragment.main

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.typeface.IIcon
import moe.tlaster.openween.adapter.BaseModelAdapter
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.api.remind.Remind
import moe.tlaster.openween.core.api.statuses.Mentions
import moe.tlaster.openween.core.model.status.MessageListModel
import moe.tlaster.openween.core.model.status.MessageModel
import moe.tlaster.openween.core.model.types.RemindType
import moe.tlaster.openween.fragment.WeiboListBase
import okhttp3.Call

/**
 * Created by Asahi on 16/11/28.
 */
class Mention(override val icon: IIcon = GoogleMaterial.Icon.gmd_forum) : WeiboListBase<MessageModel>() {

    override fun initAdapter(): BaseQuickAdapter<MessageModel, BaseViewHolder> {
        return BaseModelAdapter()
    }

    override fun loadMoreOverride(callback: Callback<List<MessageModel>>) {
        Mentions.getMentions(count = mLoadCount, max_id = mAdapter?.data?.last()?.id!!, callback = object : WeiboListCallback<MessageListModel>() {
            override fun onResponse(response: MessageListModel?, id: Int) {
                response?.statuses = response?.statuses?.subList(1, response.statuses?.lastIndex!!)
                callback.onResponse(response?.statuses!!, response?.totalNumber!!)
            }
        })
    }

    override fun refreshOverride(callback: Callback<List<MessageModel>>) {
        Remind.clearUnread(RemindType.MentionStatus)
        Mentions.getMentions(count = mLoadCount, callback = object : WeiboListCallback<MessageListModel>() {
            override fun onResponse(response: MessageListModel?, id: Int) {
                callback.onResponse(response?.statuses!!, response?.totalNumber!!)
            }
        })
    }
}