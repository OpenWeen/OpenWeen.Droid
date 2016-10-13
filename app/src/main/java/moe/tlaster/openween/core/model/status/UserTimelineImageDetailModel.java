package moe.tlaster.openween.core.model.status;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Asahi on 2016/10/10.
 */

public class UserTimelineImageDetailModel {
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
}
