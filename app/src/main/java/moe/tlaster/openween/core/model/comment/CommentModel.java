package moe.tlaster.openween.core.model.comment;

import com.google.gson.annotations.SerializedName;

import moe.tlaster.openween.core.model.BaseModel;
import moe.tlaster.openween.core.model.status.MessageModel;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class CommentModel extends BaseModel {
    @SerializedName("source_allowclick")
    private int mSourceAllowClick;
    @SerializedName("source_type")
    private int mSourceType;
    @SerializedName("status")
    private MessageModel mStatus;
    @SerializedName("reply_comment")
    private CommentModel mReplyComment;

    public int getSourceAllowClick() {
        return mSourceAllowClick;
    }

    public void setSourceAllowClick(int sourceAllowClick) {
        mSourceAllowClick = sourceAllowClick;
    }

    public int getSourceType() {
        return mSourceType;
    }

    public void setSourceType(int sourceType) {
        mSourceType = sourceType;
    }

    public MessageModel getStatus() {
        return mStatus;
    }

    public void setStatus(MessageModel status) {
        mStatus = status;
    }

    public CommentModel getReplyComment() {
        return mReplyComment;
    }

    public void setReplyComment(CommentModel replyComment) {
        mReplyComment = replyComment;
    }

    @Override
    public int getItemType() {
        return COMMENT;
    }
}
