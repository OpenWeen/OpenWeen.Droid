package moe.tlaster.openween.core.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import moe.tlaster.openween.common.helpers.TimeHelper;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class UserModel extends UserBaseModel implements Parcelable {
    @SerializedName("screen_name")
    private String mScreenName;
    @SerializedName("name")
    private String mName;
    @SerializedName("remark")
    private String mRemark;
    @SerializedName("province")
    private String mProvince;
    @SerializedName("city")
    private String mCity;
    @SerializedName("location")
    private String mLocation;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("profile_image_url")
    private String mProfileImageUrl;
    @SerializedName("domain")
    private String mDomain;
    @SerializedName("gender")
    private String mGender;
    @SerializedName("favourites_count")
    private int mFavouritesCount = 0;
    @SerializedName("verified_type")
    private int mVerifiedType;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("following")
    private boolean mFollowing = false;
    @SerializedName("allow_all_act_msg")
    private boolean mAllowAllActMsg = false;
    @SerializedName("geo_enabled")
    private boolean mGeoEnabled = false;
    @SerializedName("verified")
    private boolean mVerified = false;
    @SerializedName("allow_all_comment")
    private boolean mAllowAllComment = false;
    @SerializedName("avatar_large")
    private String mAvatarLarge;
    @SerializedName("verified_reason")
    private String mVerifiedReason;
    @SerializedName("follow_me")
    private boolean mFollowMe = false;
    @SerializedName("online_status")
    private int mOnlineStatus = 0;
    @SerializedName("bi_followers_count")
    private int mBiFollowersCount = 0;
    @SerializedName("cover_image")
    private String mCoverimage = "";
    @SerializedName("cover_image_phone")
    private String mCoverImagePhone;
    @SerializedName("avatar_hd")
    private String mAvatarHD;
    @SerializedName("weihao")
    private String mWeihao;
    @SerializedName("lang")
    private String mLang;
    @SerializedName("level")
    private int mLevel;


    public String getCreatedAtDiffForHuman(){
        try {
            return TimeHelper.getmPrettyTime().format(TimeHelper.getmSimpleDateFormat().parse(mCreatedAt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mCreatedAt;
    }

    public Date getCreatedDate(){
        try {
            return TimeHelper.getmSimpleDateFormat().parse(mCreatedAt);
        } catch (ParseException e) {
            return null;
        }
    }

    public String getScreenName() {
        return mScreenName;
    }

    public void setScreenName(String screenName) {
        mScreenName = screenName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String remark) {
        mRemark = remark;
    }

    public String getProvince() {
        return mProvince;
    }

    public void setProvince(String province) {
        mProvince = province;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getProfileImageUrl() {
        return mProfileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        mProfileImageUrl = profileImageUrl;
    }

    public String getDomain() {
        return mDomain;
    }

    public void setDomain(String domain) {
        mDomain = domain;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public int getFavouritesCount() {
        return mFavouritesCount;
    }

    public void setFavouritesCount(int favouritesCount) {
        mFavouritesCount = favouritesCount;
    }

    public int getVerifiedType() {
        return mVerifiedType;
    }

    public void setVerifiedType(int verifiedType) {
        mVerifiedType = verifiedType;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public boolean isFollowing() {
        return mFollowing;
    }

    public void setFollowing(boolean following) {
        mFollowing = following;
    }

    public boolean isAllowAllActMsg() {
        return mAllowAllActMsg;
    }

    public void setAllowAllActMsg(boolean allowAllActMsg) {
        mAllowAllActMsg = allowAllActMsg;
    }

    public boolean isGeoEnabled() {
        return mGeoEnabled;
    }

    public void setGeoEnabled(boolean geoEnabled) {
        mGeoEnabled = geoEnabled;
    }

    public boolean isVerified() {
        return mVerified;
    }

    public void setVerified(boolean verified) {
        mVerified = verified;
    }

    public boolean isAllowAllComment() {
        return mAllowAllComment;
    }

    public void setAllowAllComment(boolean allowAllComment) {
        mAllowAllComment = allowAllComment;
    }

    public String getAvatarLarge() {
        return mAvatarLarge;
    }

    public void setAvatarLarge(String avatarLarge) {
        mAvatarLarge = avatarLarge;
    }

    public String getVerifiedReason() {
        return mVerifiedReason;
    }

    public void setVerifiedReason(String verifiedReason) {
        mVerifiedReason = verifiedReason;
    }

    public boolean isFollowMe() {
        return mFollowMe;
    }

    public void setFollowMe(boolean followMe) {
        mFollowMe = followMe;
    }

    public int getOnlineStatus() {
        return mOnlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        mOnlineStatus = onlineStatus;
    }

    public int getBiFollowersCount() {
        return mBiFollowersCount;
    }

    public void setBiFollowersCount(int biFollowersCount) {
        mBiFollowersCount = biFollowersCount;
    }

    public String getCoverimage() {
        return mCoverimage;
    }

    public void setCoverimage(String coverimage) {
        mCoverimage = coverimage;
    }

    public String getCoverImagePhone() {
        return mCoverImagePhone;
    }

    public void setCoverImagePhone(String coverImagePhone) {
        mCoverImagePhone = coverImagePhone;
    }

    public String getAvatarHD() {
        return mAvatarHD;
    }

    public void setAvatarHD(String avatarHD) {
        mAvatarHD = avatarHD;
    }

    public String getWeihao() {
        return mWeihao;
    }

    public void setWeihao(String weihao) {
        mWeihao = weihao;
    }

    public String getLang() {
        return mLang;
    }

    public void setLang(String lang) {
        mLang = lang;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mScreenName);
        dest.writeString(this.mName);
        dest.writeString(this.mRemark);
        dest.writeString(this.mProvince);
        dest.writeString(this.mCity);
        dest.writeString(this.mLocation);
        dest.writeString(this.mDescription);
        dest.writeString(this.mUrl);
        dest.writeString(this.mProfileImageUrl);
        dest.writeString(this.mDomain);
        dest.writeString(this.mGender);
        dest.writeInt(this.mFavouritesCount);
        dest.writeInt(this.mVerifiedType);
        dest.writeString(this.mCreatedAt);
        dest.writeByte(this.mFollowing ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mAllowAllActMsg ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mGeoEnabled ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mVerified ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mAllowAllComment ? (byte) 1 : (byte) 0);
        dest.writeString(this.mAvatarLarge);
        dest.writeString(this.mVerifiedReason);
        dest.writeByte(this.mFollowMe ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mOnlineStatus);
        dest.writeInt(this.mBiFollowersCount);
        dest.writeString(this.mCoverimage);
        dest.writeString(this.mCoverImagePhone);
        dest.writeString(this.mAvatarHD);
        dest.writeString(this.mWeihao);
        dest.writeString(this.mLang);
        dest.writeInt(this.mLevel);
        dest.writeLong(this.mID);
        dest.writeString(this.mIDStr);
        dest.writeInt(this.mFollowersCount);
        dest.writeInt(this.mFriendsCount);
        dest.writeInt(this.mStatusesCount);
    }

    public UserModel() {
    }

    protected UserModel(Parcel in) {
        this.mScreenName = in.readString();
        this.mName = in.readString();
        this.mRemark = in.readString();
        this.mProvince = in.readString();
        this.mCity = in.readString();
        this.mLocation = in.readString();
        this.mDescription = in.readString();
        this.mUrl = in.readString();
        this.mProfileImageUrl = in.readString();
        this.mDomain = in.readString();
        this.mGender = in.readString();
        this.mFavouritesCount = in.readInt();
        this.mVerifiedType = in.readInt();
        this.mCreatedAt = in.readString();
        this.mFollowing = in.readByte() != 0;
        this.mAllowAllActMsg = in.readByte() != 0;
        this.mGeoEnabled = in.readByte() != 0;
        this.mVerified = in.readByte() != 0;
        this.mAllowAllComment = in.readByte() != 0;
        this.mAvatarLarge = in.readString();
        this.mVerifiedReason = in.readString();
        this.mFollowMe = in.readByte() != 0;
        this.mOnlineStatus = in.readInt();
        this.mBiFollowersCount = in.readInt();
        this.mCoverimage = in.readString();
        this.mCoverImagePhone = in.readString();
        this.mAvatarHD = in.readString();
        this.mWeihao = in.readString();
        this.mLang = in.readString();
        this.mLevel = in.readInt();
        this.mID = in.readLong();
        this.mIDStr = in.readString();
        this.mFollowersCount = in.readInt();
        this.mFriendsCount = in.readInt();
        this.mStatusesCount = in.readInt();
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
