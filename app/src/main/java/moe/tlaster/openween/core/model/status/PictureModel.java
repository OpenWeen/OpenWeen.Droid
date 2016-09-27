package moe.tlaster.openween.core.model.status;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class PictureModel {
    @SerializedName("pic_id")
    private String mPicID;
    @SerializedName("thumbnail_pic")
    private String mThumbnailPic;
    @SerializedName("bmiddle_pic")
    private String mBmiddlePic;
    @SerializedName("original_pic")
    private String mOriginalPic;

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
        return mThumbnailPic.replace("thumbnail", "large");
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
}
