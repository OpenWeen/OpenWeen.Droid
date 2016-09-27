package moe.tlaster.openween.core.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/8/26.
 */
public class LimitStatusModel {
    @SerializedName("ip_limit")
    private long mIpLimit;
    @SerializedName("limit_time_unit")
    private String mLimitTimeUnit;
    @SerializedName("remaining_ip_hits")
    private long mRemainingIpHits;
    @SerializedName("remaining_user_hits")
    private int mRemainingUserHits;
    @SerializedName("reset_time")
    private String mResetTimeValue;
    @SerializedName("reset_time_in_seconds")
    private long mResetTimeInSeconds;
    @SerializedName("user_limit")
    private int mUserLimit;

    public long getIpLimit() {
        return mIpLimit;
    }

    public void setIpLimit(long ipLimit) {
        mIpLimit = ipLimit;
    }

    public String getLimitTimeUnit() {
        return mLimitTimeUnit;
    }

    public void setLimitTimeUnit(String limitTimeUnit) {
        mLimitTimeUnit = limitTimeUnit;
    }

    public long getRemainingIpHits() {
        return mRemainingIpHits;
    }

    public void setRemainingIpHits(long remainingIpHits) {
        mRemainingIpHits = remainingIpHits;
    }

    public int getRemainingUserHits() {
        return mRemainingUserHits;
    }

    public void setRemainingUserHits(int remainingUserHits) {
        mRemainingUserHits = remainingUserHits;
    }

    public String getResetTimeValue() {
        return mResetTimeValue;
    }

    public void setResetTimeValue(String resetTimeValue) {
        mResetTimeValue = resetTimeValue;
    }

    public long getResetTimeInSeconds() {
        return mResetTimeInSeconds;
    }

    public void setResetTimeInSeconds(long resetTimeInSeconds) {
        mResetTimeInSeconds = resetTimeInSeconds;
    }

    public int getUserLimit() {
        return mUserLimit;
    }

    public void setUserLimit(int userLimit) {
        mUserLimit = userLimit;
    }
}
