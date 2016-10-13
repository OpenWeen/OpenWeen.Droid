package moe.tlaster.openween.core.model.url;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class ObjectModel {
    @SerializedName("url")
    private String mUrl;
    @SerializedName("display_name")
    private String mDisplayName;
    @SerializedName("pic_ids")
    private String[] mPicIds;
    @SerializedName("object_type")
    private String mObjectType;
    @SerializedName("object")
    private Object mUrlObject;
    @SerializedName("original_url")
    private String mOriginalUrl;
    @SerializedName("stream")
    private StreamModel mStream;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public String[] getPicIds() {
        return mPicIds;
    }

    public void setPicIds(String[] picIds) {
        mPicIds = picIds;
    }

    public String getObjectType() {
        return mObjectType;
    }

    public void setObjectType(String objectType) {
        mObjectType = objectType;
    }

    public Object getUrlObject() {
        return mUrlObject;
    }

    public void setUrlObject(Object urlObject) {
        mUrlObject = urlObject;
    }

    public String getOriginalUrl() {
        return mOriginalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        mOriginalUrl = originalUrl;
    }

    public StreamModel getStream() {
        return mStream;
    }

    public void setStream(StreamModel stream) {
        mStream = stream;
    }
}
