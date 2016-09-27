package moe.tlaster.openween.core.model.favor;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class FavorTagModel {
    @SerializedName("id")
    private long mID;
    @SerializedName("tag")
    private String mTag;

    public long getID() {
        return mID;
    }

    public void setID(long ID) {
        mID = ID;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
    }
}
