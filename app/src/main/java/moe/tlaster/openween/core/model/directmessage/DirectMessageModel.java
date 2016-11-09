package moe.tlaster.openween.core.model.directmessage;

import com.google.gson.annotations.SerializedName;

import moe.tlaster.openween.core.model.user.UserModel;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class DirectMessageModel {
    @SerializedName("id")
    private long mID;
    @SerializedName("idstr")
    private String mIDStr;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("text")
    private String mText;
    @SerializedName("sender_id")
    private long mSenderID;
    @SerializedName("recipient_id")
    private long mRecipientID;
    @SerializedName("recipient")
    private UserModel mRecipient;
    @SerializedName("sender")
    private UserModel mSender;
    @SerializedName("sender_screen_name")
    private String mSenderScreenName;
    @SerializedName("recipient_screen_name")
    private String mRecipientScreenName;
    @SerializedName("att_ids")
    private long[] AttIDs = { 0, 0 };

    public long getID() {
        return mID;
    }

    public void setID(long ID) {
        mID = ID;
    }

    public String getIDStr() {
        return mIDStr;
    }

    public void setIDStr(String IDStr) {
        mIDStr = IDStr;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public long getSenderID() {
        return mSenderID;
    }

    public void setSenderID(long senderID) {
        mSenderID = senderID;
    }

    public long getRecipientID() {
        return mRecipientID;
    }

    public void setRecipientID(long recipientID) {
        mRecipientID = recipientID;
    }

    public UserModel getRecipient() {
        return mRecipient;
    }

    public void setRecipient(UserModel recipient) {
        mRecipient = recipient;
    }

    public UserModel getSender() {
        return mSender;
    }

    public void setSender(UserModel sender) {
        mSender = sender;
    }

    public String getSenderScreenName() {
        return mSenderScreenName;
    }

    public void setSenderScreenName(String senderScreenName) {
        mSenderScreenName = senderScreenName;
    }

    public String getRecipientScreenName() {
        return mRecipientScreenName;
    }

    public void setRecipientScreenName(String recipientScreenName) {
        mRecipientScreenName = recipientScreenName;
    }

    public long[] getAttIDs() {
        return AttIDs;
    }

    public void setAttIDs(long[] attIDs) {
        AttIDs = attIDs;
    }
}
