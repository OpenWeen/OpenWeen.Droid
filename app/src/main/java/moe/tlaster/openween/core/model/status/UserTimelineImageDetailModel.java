package moe.tlaster.openween.core.model.status;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Asahi on 2016/10/10.
 */

public class UserTimelineImageDetailModel implements Parcelable {
    @SerializedName("url")
    private String mUrl;
    @SerializedName("width")
    private int mWidth;
    @SerializedName("height")
    private int mHeight;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mUrl);
        dest.writeInt(this.mWidth);
        dest.writeInt(this.mHeight);
    }

    public UserTimelineImageDetailModel() {
    }

    protected UserTimelineImageDetailModel(Parcel in) {
        this.mUrl = in.readString();
        this.mWidth = in.readInt();
        this.mHeight = in.readInt();
    }

    public static final Parcelable.Creator<UserTimelineImageDetailModel> CREATOR = new Parcelable.Creator<UserTimelineImageDetailModel>() {
        @Override
        public UserTimelineImageDetailModel createFromParcel(Parcel source) {
            return new UserTimelineImageDetailModel(source);
        }

        @Override
        public UserTimelineImageDetailModel[] newArray(int size) {
            return new UserTimelineImageDetailModel[size];
        }
    };
}
