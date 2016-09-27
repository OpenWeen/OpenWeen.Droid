package moe.tlaster.openween.core.model.favor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import moe.tlaster.openween.core.model.status.MessageModel;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class FavorModel {
    @SerializedName("status")
    private MessageModel mStatus;
    @SerializedName("favorited_time")
    private String mFavoritedTime;
    @SerializedName("tags")
    private List<FavorTagModel> mTags;

    public MessageModel getStatus() {
        return mStatus;
    }

    public void setStatus(MessageModel status) {
        mStatus = status;
    }

    public String getFavoritedTime() {
        return mFavoritedTime;
    }

    public void setFavoritedTime(String favoritedTime) {
        mFavoritedTime = favoritedTime;
    }

    public List<FavorTagModel> getTags() {
        return mTags;
    }

    public void setTags(List<FavorTagModel> tags) {
        mTags = tags;
    }
}
