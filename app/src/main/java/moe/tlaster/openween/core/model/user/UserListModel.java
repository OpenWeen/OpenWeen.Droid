package moe.tlaster.openween.core.model.user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import moe.tlaster.openween.core.model.BaseListModel;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class UserListModel extends BaseListModel {
    @SerializedName("users")
    private List<UserModel> mUsers;
}
