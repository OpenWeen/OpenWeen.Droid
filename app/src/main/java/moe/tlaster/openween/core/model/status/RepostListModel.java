package moe.tlaster.openween.core.model.status;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import moe.tlaster.openween.core.model.BaseListModel;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class RepostListModel extends BaseListModel {

    @SerializedName("reposts")
    public List<MessageModel> mReposts;

    public List<MessageModel> getReposts() {
        return mReposts;
    }

    public void setReposts(List<MessageModel> reposts) {
        mReposts = reposts;
    }
}
