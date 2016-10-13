package moe.tlaster.openween.core.model.status;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asahi on 2016/10/8.
 */

public class MediaModel {
    public MediaModel(String fid) {
        mFid = fid;
    }

    @SerializedName("bypass")
    private String mByPass = "unistore.image";
    @SerializedName("createtype")
    private String mCreateType = "localfile";
    @SerializedName("filterName")
    private String mFilterName = "";
    @SerializedName("stickerID")
    private String mStickerID = "";
    @SerializedName("fid")
    private String mFid = "";
    @SerializedName("type")
    private String mType = "pic";
    @SerializedName("filterID")
    private String mFilterID = "";
    @SerializedName("picStatus")
    private int mPicStatus = 0;

    @Override
    public String toString() {
        return "[" + new Gson().toJson(this) + "]";
    }
}
