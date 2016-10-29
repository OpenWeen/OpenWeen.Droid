package moe.tlaster.openween.core.model.status;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import moe.tlaster.openween.core.model.types.WeiboVisibility;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class WeiboVisibilityModel implements Parcelable {
    @SerializedName("type")
    private WeiboVisibility mVisibility;
    @SerializedName("list_id")
    private int mListID;

    public WeiboVisibility getVisibility() {
        return mVisibility;
    }

    public void setVisibility(WeiboVisibility visibility) {
        mVisibility = visibility;
    }

    public int getListID() {
        return mListID;
    }

    public void setListID(int listID) {
        mListID = listID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mVisibility == null ? -1 : this.mVisibility.ordinal());
        dest.writeInt(this.mListID);
    }

    public WeiboVisibilityModel() {
    }

    protected WeiboVisibilityModel(Parcel in) {
        int tmpMVisibility = in.readInt();
        this.mVisibility = tmpMVisibility == -1 ? null : WeiboVisibility.values()[tmpMVisibility];
        this.mListID = in.readInt();
    }

    public static final Parcelable.Creator<WeiboVisibilityModel> CREATOR = new Parcelable.Creator<WeiboVisibilityModel>() {
        @Override
        public WeiboVisibilityModel createFromParcel(Parcel source) {
            return new WeiboVisibilityModel(source);
        }

        @Override
        public WeiboVisibilityModel[] newArray(int size) {
            return new WeiboVisibilityModel[size];
        }
    };
}
