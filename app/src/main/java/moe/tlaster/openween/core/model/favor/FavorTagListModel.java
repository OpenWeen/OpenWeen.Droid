package moe.tlaster.openween.core.model.favor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class FavorTagListModel {
    @SerializedName("tags")
    private List<FavorTagModel> mTags;
    @SerializedName("total_number")
    private int mTotalNumber;

    public List<FavorTagModel> getTags() {
        return mTags;
    }

    public void setTags(List<FavorTagModel> tags) {
        mTags = tags;
    }

    public int getTotalNumber() {
        return mTotalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        mTotalNumber = totalNumber;
    }
}
