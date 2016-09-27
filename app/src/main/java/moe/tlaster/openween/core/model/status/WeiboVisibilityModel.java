package moe.tlaster.openween.core.model.status;

import com.google.gson.annotations.SerializedName;

import moe.tlaster.openween.core.model.types.WeiboVisibility;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class WeiboVisibilityModel {
    @SerializedName("type")
    private WeiboVisibility mVisibility;
    @SerializedName("list_id")
    private int mListID;

    public WeiboVisibility getVisibility() {
        return mVisibility;
    }

    public void setVisibility(WeiboVisibility visibility) {
        mVisibility = visibility;
    }

    public int getListID() {
        return mListID;
    }

    public void setListID(int listID) {
        mListID = listID;
    }
}
