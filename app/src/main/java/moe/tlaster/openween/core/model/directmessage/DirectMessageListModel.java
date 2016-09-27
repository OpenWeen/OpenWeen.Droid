package moe.tlaster.openween.core.model.directmessage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import moe.tlaster.openween.core.model.BaseListModel;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class DirectMessageListModel extends BaseListModel {
    @SerializedName("direct_messages")
    private List<DirectMessageModel> mDirectMessages;

    public List<DirectMessageModel> getDirectMessages() {
        return mDirectMessages;
    }

    public void setDirectMessages(List<DirectMessageModel> directMessages) {
        mDirectMessages = directMessages;
    }

}
