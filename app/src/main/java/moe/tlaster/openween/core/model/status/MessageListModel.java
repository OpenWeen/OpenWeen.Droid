package moe.tlaster.openween.core.model.status;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import moe.tlaster.openween.core.model.BaseListModel;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class MessageListModel extends BaseListModel {
    @SerializedName("statuses")
    private List<MessageModel> mStatuses;

    public List<MessageModel> getStatuses() {
        return mStatuses;
    }

    public void setStatuses(List<MessageModel> statuses) {
        mStatuses = statuses;
    }
}
