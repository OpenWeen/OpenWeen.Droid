package moe.tlaster.openween.core.model.directmessage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import moe.tlaster.openween.core.model.BaseListModel;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class DirectMessageUserListModel extends BaseListModel {
    @SerializedName("user_list")
    private List<DirectMessageUserModel> mUserList;
    @SerializedName("totalNumber")
    private int mTotalNumber;

    public List<DirectMessageUserModel> getUserList() {
        return mUserList;
    }

    public void setUserList(List<DirectMessageUserModel> userList) {
        mUserList = userList;
    }

    @Override
    public int getTotalNumber() {
        return mTotalNumber;
    }

    @Override
    public void setTotalNumber(int totalNumber) {
        mTotalNumber = totalNumber;
    }
}
