package moe.tlaster.openween.core.model.status;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Asahi on 2016/10/10.
 */

public class UserTimelineImageModel implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mThumbnail, flags);
        dest.writeParcelable(this.mBmiddle, flags);
        dest.writeParcelable(this.mMiddleplus, flags);
        dest.writeParcelable(this.mLarge, flags);
        dest.writeParcelable(this.mOriginal, flags);
        dest.writeParcelable(this.mLargest, flags);
        dest.writeString(this.mPicId);
    }

    public UserTimelineImageModel() {
    }

    protected UserTimelineImageModel(Parcel in) {
        this.mThumbnail = in.readParcelable(UserTimelineImageDetailModel.class.getClassLoader());
        this.mBmiddle = in.readParcelable(UserTimelineImageDetailModel.class.getClassLoader());
        this.mMiddleplus = in.readParcelable(UserTimelineImageDetailModel.class.getClassLoader());
        this.mLarge = in.readParcelable(UserTimelineImageDetailModel.class.getClassLoader());
        this.mOriginal = in.readParcelable(UserTimelineImageDetailModel.class.getClassLoader());
        this.mLargest = in.readParcelable(UserTimelineImageDetailModel.class.getClassLoader());
        this.mPicId = in.readString();
    }

    public static final Parcelable.Creator<UserTimelineImageModel> CREATOR = new Parcelable.Creator<UserTimelineImageModel>() {
        @Override
        public UserTimelineImageModel createFromParcel(Parcel source) {
            return new UserTimelineImageModel(source);
        }

        @Override
        public UserTimelineImageModel[] newArray(int size) {
            return new UserTimelineImageModel[size];
        }
    };
}
