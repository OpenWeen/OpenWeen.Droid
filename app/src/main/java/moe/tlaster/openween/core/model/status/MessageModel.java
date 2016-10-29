package moe.tlaster.openween.core.model.status;

import android.os.Parcel;
import android.os.Parcelable;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import moe.tlaster.openween.core.model.BaseModel;
import moe.tlaster.openween.core.model.GeoModel;
import moe.tlaster.openween.core.model.user.UserModel;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class MessageModel extends BaseModel implements Parcelable {
    @SerializedName("favorited")
    private boolean mFavorited;
    @SerializedName("truncated")
    private boolean mTruncated;
    @SerializedName("liked")
    private boolean mLiked;
    @SerializedName("in_reply_to_status_id")
    private String mInReplyToStatusID;
    @SerializedName("in_reply_to_user_id")
    private String mInReplyToUserID;
    @SerializedName("in_reply_to_screen_name")
    private String mInReplyToScreenName;
    @SerializedName("thumbnail_pic")
    private String mThumbnailPic;
    @SerializedName("bmiddle_pic")
    private String mBmiddlePic;
    @SerializedName("original_pic")
    private String mOriginalPic;
    @SerializedName("geo")
    private GeoModel mGeo;
    @SerializedName("retweeted_status")
    private MessageModel mRetweetedStatus;
    @SerializedName("reposts_count")
    private int mRepostsCount;
    @SerializedName("comments_count")
    private int mCommentsCount;
    @SerializedName("attitudes_count")
    private int mAttitudesCount;
    @SerializedName("mlevel")
    private int mMLevel;
    @SerializedName("longText")
    private LongTextModel mLongText;
    @SerializedName("visible")
    private WeiboVisibilityModel mVisible;
    @SerializedName("pic_urls")
    private List<PictureModel> mPicUrls;
    @SerializedName("pic_infos")
    private Map<String, UserTimelineImageModel> mUserTimelineImage;

    public Map<String, UserTimelineImageModel> getUserTimelineImage() {
        return mUserTimelineImage;
    }

    public void setUserTimelineImage(Map<String, UserTimelineImageModel> userTimelineImage) {
        mUserTimelineImage = userTimelineImage;
    }

    public boolean isFavorited() {
        return mFavorited;
    }

    public void setFavorited(boolean favorited) {
        mFavorited = favorited;
    }

    public boolean isTruncated() {
        return mTruncated;
    }

    public void setTruncated(boolean truncated) {
        mTruncated = truncated;
    }

    public boolean isLiked() {
        return mLiked;
    }

    public void setLiked(boolean liked) {
        mLiked = liked;
    }

    public String getInReplyToStatusID() {
        return mInReplyToStatusID;
    }

    public void setInReplyToStatusID(String inReplyToStatusID) {
        mInReplyToStatusID = inReplyToStatusID;
    }

    public String getInReplyToUserID() {
        return mInReplyToUserID;
    }

    public void setInReplyToUserID(String inReplyToUserID) {
        mInReplyToUserID = inReplyToUserID;
    }

    public String getInReplyToScreenName() {
        return mInReplyToScreenName;
    }

    public void setInReplyToScreenName(String inReplyToScreenName) {
        mInReplyToScreenName = inReplyToScreenName;
    }

    public String getThumbnailPic() {
        return mThumbnailPic;
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

    public GeoModel getGeo() {
        return mGeo;
    }

    public void setGeo(GeoModel geo) {
        mGeo = geo;
    }

    public MessageModel getRetweetedStatus() {
        return mRetweetedStatus;
    }

    public void setRetweetedStatus(MessageModel retweetedStatus) {
        mRetweetedStatus = retweetedStatus;
    }

    public int getRepostsCount() {
        return mRepostsCount;
    }

    public void setRepostsCount(int repostsCount) {
        mRepostsCount = repostsCount;
    }

    public int getCommentsCount() {
        return mCommentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        mCommentsCount = commentsCount;
    }

    public int getAttitudesCount() {
        return mAttitudesCount;
    }

    public void setAttitudesCount(int attitudesCount) {
        mAttitudesCount = attitudesCount;
    }

    public int getMLevel() {
        return mMLevel;
    }

    public void setMLevel(int MLevel) {
        mMLevel = MLevel;
    }

    public LongTextModel getLongText() {
        return mLongText;
    }

    public void setLongText(LongTextModel longText) {
        mLongText = longText;
    }

    public WeiboVisibilityModel getVisible() {
        return mVisible;
    }

    public void setVisible(WeiboVisibilityModel visible) {
        mVisible = visible;
    }

    public List<PictureModel> getPicUrls() {
        return mPicUrls == null && mUserTimelineImage != null ?
                Stream.of(mUserTimelineImage.values()).map(PictureModel::new).collect(Collectors.toCollection(ArrayList<PictureModel>::new)) :
                mPicUrls;
    }

    public void setPicUrls(List<PictureModel> picUrls) {
        mPicUrls = picUrls;
    }

    @Override
    public int getItemType() {
        return MESSAGE;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.mFavorited ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mTruncated ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mLiked ? (byte) 1 : (byte) 0);
        dest.writeString(this.mInReplyToStatusID);
        dest.writeString(this.mInReplyToUserID);
        dest.writeString(this.mInReplyToScreenName);
        dest.writeString(this.mThumbnailPic);
        dest.writeString(this.mBmiddlePic);
        dest.writeString(this.mOriginalPic);
        dest.writeParcelable(this.mGeo, flags);
        dest.writeParcelable(this.mRetweetedStatus, flags);
        dest.writeInt(this.mRepostsCount);
        dest.writeInt(this.mCommentsCount);
        dest.writeInt(this.mAttitudesCount);
        dest.writeInt(this.mMLevel);
        dest.writeParcelable(this.mLongText, flags);
        dest.writeParcelable(this.mVisible, flags);
        dest.writeList(this.mPicUrls);
        if (mUserTimelineImage != null) {
            dest.writeInt(this.mUserTimelineImage.size());
            for (Map.Entry<String, UserTimelineImageModel> entry : this.mUserTimelineImage.entrySet()) {
                dest.writeString(entry.getKey());
                dest.writeParcelable(entry.getValue(), flags);
            }
        } else {
            dest.writeInt(-1);
        }
        dest.writeLong(this.mID);
        dest.writeLong(this.mMID);
        dest.writeString(this.mIDStr);
        dest.writeString(this.mCreatedAt);
        dest.writeString(this.mText);
        dest.writeString(this.mSource);
        dest.writeParcelable(this.mUser, flags);
    }

    public MessageModel() {
    }

    protected MessageModel(Parcel in) {
        this.mFavorited = in.readByte() != 0;
        this.mTruncated = in.readByte() != 0;
        this.mLiked = in.readByte() != 0;
        this.mInReplyToStatusID = in.readString();
        this.mInReplyToUserID = in.readString();
        this.mInReplyToScreenName = in.readString();
        this.mThumbnailPic = in.readString();
        this.mBmiddlePic = in.readString();
        this.mOriginalPic = in.readString();
        this.mGeo = in.readParcelable(GeoModel.class.getClassLoader());
        this.mRetweetedStatus = in.readParcelable(MessageModel.class.getClassLoader());
        this.mRepostsCount = in.readInt();
        this.mCommentsCount = in.readInt();
        this.mAttitudesCount = in.readInt();
        this.mMLevel = in.readInt();
        this.mLongText = in.readParcelable(LongTextModel.class.getClassLoader());
        this.mVisible = in.readParcelable(WeiboVisibilityModel.class.getClassLoader());
        this.mPicUrls = new ArrayList<PictureModel>();
        in.readList(this.mPicUrls, PictureModel.class.getClassLoader());
        int mUserTimelineImageSize = in.readInt();
        if (mUserTimelineImageSize != -1) {
            this.mUserTimelineImage = new HashMap<String, UserTimelineImageModel>(mUserTimelineImageSize);
            for (int i = 0; i < mUserTimelineImageSize; i++) {
                String key = in.readString();
                UserTimelineImageModel value = in.readParcelable(UserTimelineImageModel.class.getClassLoader());
                this.mUserTimelineImage.put(key, value);
            }
        }
        this.mID = in.readLong();
        this.mMID = in.readLong();
        this.mIDStr = in.readString();
        this.mCreatedAt = in.readString();
        this.mText = in.readString();
        this.mSource = in.readString();
        this.mUser = in.readParcelable(UserModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<MessageModel> CREATOR = new Parcelable.Creator<MessageModel>() {
        @Override
        public MessageModel createFromParcel(Parcel source) {
            return new MessageModel(source);
        }

        @Override
        public MessageModel[] newArray(int size) {
            return new MessageModel[size];
        }
    };
}
