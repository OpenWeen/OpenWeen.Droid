package moe.tlaster.openween.core.model.status;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import moe.tlaster.openween.core.model.BaseModel;
import moe.tlaster.openween.core.model.GeoModel;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class MessageModel extends BaseModel {
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
}
