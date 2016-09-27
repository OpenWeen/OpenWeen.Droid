package moe.tlaster.openween.core.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/8/26.
 */
public class BaseListModel {
    @SerializedName("previous_cursor")
    private String mPreviousCursor;
    @SerializedName("next_cursor")
    private String mNextCursor;
    @SerializedName("total_number")
    private int mTotalNumber;

    public String getPreviousCursor() {
        return mPreviousCursor;
    }

    public void setPreviousCursor(String previousCursor) {
        mPreviousCursor = previousCursor;
    }

    public String getNextCursor() {
        return mNextCursor;
    }

    public void setNextCursor(String nextCursor) {
        mNextCursor = nextCursor;
    }

    public int getTotalNumber() {
        return mTotalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        mTotalNumber = totalNumber;
    }
}
