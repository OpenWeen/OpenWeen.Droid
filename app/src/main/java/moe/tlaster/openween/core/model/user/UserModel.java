package moe.tlaster.openween.core.model.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class UserModel {
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
    private String mCoverimage;
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
}
