package moe.tlaster.openween.adapter

import android.view.View

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import me.himanshusoni.chatmessageview.ChatMessageView
import moe.tlaster.openween.R
import moe.tlaster.openween.common.StaticResource
import moe.tlaster.openween.core.model.directmessage.DirectMessageModel

/**
 * Created by Asahi on 2016/11/7.
 */

class DirectMessageAdapter : BaseQuickAdapter<DirectMessageModel, BaseViewHolder>(R.layout.direct_message_item_template, null) {

    override fun convert(baseViewHolder: BaseViewHolder, directMessageModel: DirectMessageModel) {
        if (directMessageModel.senderID == StaticResource.uid) {
            baseViewHolder.getView<View>(R.id.direct_message_list_from_me_container).visibility = View.VISIBLE
            baseViewHolder.getView<View>(R.id.direct_message_list_from_other_container).visibility = View.GONE
            baseViewHolder.setText(R.id.direct_message_list_from_me_text, directMessageModel.text)
        } else {
            baseViewHolder.getView<View>(R.id.direct_message_list_from_me_container).visibility = View.GONE
            baseViewHolder.getView<View>(R.id.direct_message_list_from_other_container).visibility = View.VISIBLE
            baseViewHolder.setText(R.id.direct_message_list_from_other_text, directMessageModel.text)
        }
    }
}
