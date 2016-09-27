package moe.tlaster.openween.core.model.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class UserBaseModel {
    @SerializedName("id")
    public long mID;
    @SerializedName("idstr")
    public String mIDStr;
    @SerializedName("followers_count")
    public int mFollowersCount = 0;
    @SerializedName("friends_count")
    public int mFriendsCount = 0;
    @SerializedName("statuses_count")
    public int mStatusesCount = 0;

    public long getID() {
        return mID;
    }

    public void setID(long ID) {
        mID = ID;
    }

    public String getIDStr() {
        return mIDStr;
    }

    public void setIDStr(String IDStr) {
        mIDStr = IDStr;
    }

    public int getFollowersCount() {
        return mFollowersCount;
    }

    public void setFollowersCount(int followersCount) {
        mFollowersCount = followersCount;
    }

    public int getFriendsCount() {
        return mFriendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        mFriendsCount = friendsCount;
    }

    public int getStatusesCount() {
        return mStatusesCount;
    }

    public void setStatusesCount(int statusesCount) {
        mStatusesCount = statusesCount;
    }
}
