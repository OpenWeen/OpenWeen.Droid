package moe.tlaster.openween.core.model.block;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class BlockListModel {
    @SerializedName("users")
    private List<BlockModel> mUsers;

    public List<BlockModel> getUsers() {
        return mUsers;
    }

    public void setUsers(List<BlockModel> users) {
        mUsers = users;
    }
}
