package moe.tlaster.openween.core.model.status;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Asahi on 2016/10/10.
 */

public class UserTimelineImageModel {
    @SerializedName("thumbnail")
    private UserTimelineImageDetailModel mThumbnail;
    @SerializedName("bmiddle")
    private UserTimelineImageDetailModel mBmiddle;
    @SerializedName("middleplus")
    private UserTimelineImageDetailModel mMiddleplus;
    @SerializedName("large")
    private UserTimelineImageDetailModel mLarge;
    @SerializedName("original")
    private UserTimelineImageDetailModel mOriginal;
    @SerializedName("largest")
    private UserTimelineImageDetailModel mLargest;
    @SerializedName("pic_id")
    private String mPicId;

    public UserTimelineImageDetailModel getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(UserTimelineImageDetailModel thumbnail) {
        mThumbnail = thumbnail;
    }

    public UserTimelineImageDetailModel getBmiddle() {
        return mBmiddle;
    }

    public void setBmiddle(UserTimelineImageDetailModel bmiddle) {
        mBmiddle = bmiddle;
    }

    public UserTimelineImageDetailModel getMiddleplus() {
        return mMiddleplus;
    }

    public void setMiddleplus(UserTimelineImageDetailModel middleplus) {
        mMiddleplus = middleplus;
    }

    public UserTimelineImageDetailModel getLarge() {
        return mLarge;
    }

    public void setLarge(UserTimelineImageDetailModel large) {
        mLarge = large;
    }

    public UserTimelineImageDetailModel getOriginal() {
        return mOriginal;
    }

    public void setOriginal(UserTimelineImageDetailModel original) {
        mOriginal = original;
    }

    public UserTimelineImageDetailModel getLargest() {
        return mLargest;
    }

    public void setLargest(UserTimelineImageDetailModel largest) {
        mLargest = largest;
    }

    public String getPicId() {
        return mPicId;
    }

    public void setPicId(String picId) {
        mPicId = picId;
    }
}
