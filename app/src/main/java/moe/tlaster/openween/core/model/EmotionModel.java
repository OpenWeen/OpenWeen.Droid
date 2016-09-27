package moe.tlaster.openween.core.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/8/26.
 */
public class EmotionModel {
    @SerializedName("phrase")
    private String mPhrase;
    @SerializedName("type")
    private String mType;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("hot")
    private boolean mHot;
    @SerializedName("common")
    private boolean mCommon;
    @SerializedName("category")
    private String mCategory;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("value")
    private String mValue;
    @SerializedName("picid")
    private String mPicID;

    public String getPhrase() {
        return mPhrase;
    }

    public void setPhrase(String phrase) {
        mPhrase = phrase;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public boolean isHot() {
        return mHot;
    }

    public void setHot(boolean hot) {
        mHot = hot;
    }

    public boolean isCommon() {
        return mCommon;
    }

    public void setCommon(boolean common) {
        mCommon = common;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getPicID() {
        return mPicID;
    }

    public void setPicID(String picID) {
        mPicID = picID;
    }
}
