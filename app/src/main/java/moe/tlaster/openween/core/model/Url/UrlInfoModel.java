package moe.tlaster.openween.core.model.Url;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class UrlInfoModel {
    @SerializedName("result")
    private boolean mResult;
    @SerializedName("last_modified")
    private long mLastModified;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("url_short")
    private String mUrlShort;
    @SerializedName("url_long")
    private String mUrlLong;
    @SerializedName("annotations")
    private Object mAnnotations;
    @SerializedName("type")
    private int mType;

    public boolean isResult() {
        return mResult;
    }

    public void setResult(boolean result) {
        mResult = result;
    }

    public long getLastModified() {
        return mLastModified;
    }

    public void setLastModified(long lastModified) {
        mLastModified = lastModified;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getUrlShort() {
        return mUrlShort;
    }

    public void setUrlShort(String urlShort) {
        mUrlShort = urlShort;
    }

    public String getUrlLong() {
        return mUrlLong;
    }

    public void setUrlLong(String urlLong) {
        mUrlLong = urlLong;
    }

    public Object getAnnotations() {
        return mAnnotations;
    }

    public void setAnnotations(Object annotations) {
        mAnnotations = annotations;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }
}