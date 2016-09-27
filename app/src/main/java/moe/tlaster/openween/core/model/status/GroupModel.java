package moe.tlaster.openween.core.model.status;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class GroupModel {
    @SerializedName("id")
    private long mID;
    @SerializedName("idstr")
    private String mIDStr;
    @SerializedName("name")
    private String mName;

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

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
