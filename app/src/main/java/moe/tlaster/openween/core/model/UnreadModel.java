package moe.tlaster.openween.core.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tlaster on 2016/8/26.
 */
public class UnreadModel {
    @SerializedName("status")
    private int mStatus = 0;
    @SerializedName("follower")
    private int mFollower = 0;
    @SerializedName("cmt")
    private int mCmt = 0;
    @SerializedName("dm")
    private int mDm = 0;
    @SerializedName("mention_status")
    private int mMentionStatus = 0;
    @SerializedName("mention_cmt")
    private int mMentionCmt = 0;
    @SerializedName("group")
    private int mGroup = 0;
    @SerializedName("private_group")
    private int mPrivateGroup = 0;
    @SerializedName("notice")
    private int mNotice = 0;
    @SerializedName("invite")
    private int mInvite = 0;
    @SerializedName("badge")
    private int mBadge = 0;
    @SerializedName("photo")
    private int mPhoto = 0;
    @SerializedName("msgbox")
    private int mMsgBox = 0;

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public int getFollower() {
        return mFollower;
    }

    public void setFollower(int follower) {
        mFollower = follower;
    }

    public int getCmt() {
        return mCmt;
    }

    public void setCmt(int cmt) {
        mCmt = cmt;
    }

    public int getDm() {
        return mDm;
    }

    public void setDm(int dm) {
        mDm = dm;
    }

    public int getMentionStatus() {
        return mMentionStatus;
    }

    public void setMentionStatus(int mentionStatus) {
        mMentionStatus = mentionStatus;
    }

    public int getMentionCmt() {
        return mMentionCmt;
    }

    public void setMentionCmt(int mentionCmt) {
        mMentionCmt = mentionCmt;
    }

    public int getGroup() {
        return mGroup;
    }

    public void setGroup(int group) {
        mGroup = group;
    }

    public int getPrivateGroup() {
        return mPrivateGroup;
    }

    public void setPrivateGroup(int privateGroup) {
        mPrivateGroup = privateGroup;
    }

    public int getNotice() {
        return mNotice;
    }

    public void setNotice(int notice) {
        mNotice = notice;
    }

    public int getInvite() {
        return mInvite;
    }

    public void setInvite(int invite) {
        mInvite = invite;
    }

    public int getBadge() {
        return mBadge;
    }

    public void setBadge(int badge) {
        mBadge = badge;
    }

    public int getPhoto() {
        return mPhoto;
    }

    public void setPhoto(int photo) {
        mPhoto = photo;
    }

    public int getMsgBox() {
        return mMsgBox;
    }

    public void setMsgBox(int msgBox) {
        mMsgBox = msgBox;
    }
}
