package moe.tlaster.openween.core.model.directmessage;

import com.google.gson.annotations.SerializedName;

import moe.tlaster.openween.core.model.user.UserModel;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class DirectMessageUserModel {
    @SerializedName("user")
    private UserModel mUser;
    @SerializedName("direct_message")
    private DirectMessageModel mDirectMessage;

    public UserModel getUser() {
        return mUser;
    }

    public void setUser(UserModel user) {
        mUser = user;
    }

    public DirectMessageModel getDirectMessage() {
        return mDirectMessage;
    }

    public void setDirectMessage(DirectMessageModel directMessage) {
        mDirectMessage = directMessage;
    }
}
