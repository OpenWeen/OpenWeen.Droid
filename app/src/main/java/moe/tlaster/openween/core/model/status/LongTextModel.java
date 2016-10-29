package moe.tlaster.openween.core.model.status;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class LongTextModel implements Parcelable {
    @SerializedName("longTextContent")
    private String mContent;

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mContent);
    }

    public LongTextModel() {
    }

    protected LongTextModel(Parcel in) {
        this.mContent = in.readString();
    }

    public static final Parcelable.Creator<LongTextModel> CREATOR = new Parcelable.Creator<LongTextModel>() {
        @Override
        public LongTextModel createFromParcel(Parcel source) {
            return new LongTextModel(source);
        }

        @Override
        public LongTextModel[] newArray(int size) {
            return new LongTextModel[size];
        }
    };
}
