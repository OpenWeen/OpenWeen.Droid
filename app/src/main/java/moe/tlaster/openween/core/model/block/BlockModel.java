package moe.tlaster.openween.core.model.block;

import com.google.gson.annotations.SerializedName;

import moe.tlaster.openween.core.model.user.UserModel;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class BlockModel {
    @SerializedName("user")
    private UserModel mUser;
    @SerializedName("created_at")
    private String mCreatedAt;

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public UserModel getUser() {
        return mUser;
    }

    public void setUser(UserModel user) {
        mUser = user;
    }

}
