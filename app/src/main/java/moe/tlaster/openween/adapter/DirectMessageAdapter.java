package moe.tlaster.openween.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.himanshusoni.chatmessageview.ChatMessageView;
import moe.tlaster.openween.R;
import moe.tlaster.openween.common.StaticResource;
import moe.tlaster.openween.core.model.directmessage.DirectMessageModel;

/**
 * Created by Asahi on 2016/11/7.
 */

public class DirectMessageAdapter extends BaseQuickAdapter<DirectMessageModel> {

    public DirectMessageAdapter() {
        super(R.layout.direct_message_item_template, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DirectMessageModel directMessageModel) {
        if (directMessageModel.getSenderID() == StaticResource.getUid()) {
            baseViewHolder.getView(R.id.direct_message_list_from_me_container).setVisibility(View.VISIBLE);
            baseViewHolder.getView(R.id.direct_message_list_from_other_container).setVisibility(View.GONE);
            baseViewHolder.setText(R.id.direct_message_list_from_me_text, directMessageModel.getText());
        } else {
            baseViewHolder.getView(R.id.direct_message_list_from_me_container).setVisibility(View.GONE);
            baseViewHolder.getView(R.id.direct_message_list_from_other_container).setVisibility(View.VISIBLE);
            baseViewHolder.setText(R.id.direct_message_list_from_other_text, directMessageModel.getText());
        }
    }
}
