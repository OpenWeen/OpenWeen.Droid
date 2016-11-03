package moe.tlaster.openween.core.model.status;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class PictureModel implements Parcelable {
    @SerializedName("pic_id")
    private String mPicID;
    @SerializedName("thumbnail_pic")
    private String mThumbnailPic;
    @SerializedName("bmiddle_pic")
    private String mBmiddlePic;
    @SerializedName("original_pic")
    private String mOriginalPic;

    public PictureModel() {

    }

    PictureModel(UserTimelineImageModel item) {
        if (item != null){
            mPicID = item.getPicId();
            mThumbnailPic = item.getThumbnail().getUrl();
            mBmiddlePic = item.getBmiddle().getUrl();
            mOriginalPic = item.getOriginal().getUrl();
        }
    }

    public String getPicID() {
        return mPicID;
    }

    public void setPicID(String picID) {
        mPicID = picID;
    }

    public String getThumbnailPic() {
        return mThumbnailPic;
    }
    public String toLarge() {
        if (TextUtils.isEmpty(mOriginalPic))
            return mThumbnailPic.replace("thumbnail", "large");
        else
            return mOriginalPic;
    }
    public String toBmiddle() {
        return mThumbnailPic.replace("thumbnail", "bmiddle");
    }

    public void setThumbnailPic(String thumbnailPic) {
        mThumbnailPic = thumbnailPic;
    }

    public String getBmiddlePic() {
        return mBmiddlePic;
    }

    public void setBmiddlePic(String bmiddlePic) {
        mBmiddlePic = bmiddlePic;
    }

    public String getOriginalPic() {
        return mOriginalPic;
    }

    public void setOriginalPic(String originalPic) {
        mOriginalPic = originalPic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPicID);
        dest.writeString(this.mThumbnailPic);
        dest.writeString(this.mBmiddlePic);
        dest.writeString(this.mOriginalPic);
    }

    protected PictureModel(Parcel in) {
        this.mPicID = in.readString();
        this.mThumbnailPic = in.readString();
        this.mBmiddlePic = in.readString();
        this.mOriginalPic = in.readString();
    }

    public static final Parcelable.Creator<PictureModel> CREATOR = new Parcelable.Creator<PictureModel>() {
        @Override
        public PictureModel createFromParcel(Parcel source) {
            return new PictureModel(source);
        }

        @Override
        public PictureModel[] newArray(int size) {
            return new PictureModel[size];
        }
    };
}
