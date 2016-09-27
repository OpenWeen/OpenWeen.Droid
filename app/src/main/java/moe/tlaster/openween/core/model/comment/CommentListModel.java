package moe.tlaster.openween.core.model.comment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import moe.tlaster.openween.core.model.BaseListModel;

/**
 * Created by Tlaster on 2016/9/2.
 */
public class CommentListModel extends BaseListModel {
    @SerializedName("comments")
    private List<CommentModel> mComments;

    public List<CommentModel> getComments() {
        return mComments;
    }

    public void setComments(List<CommentModel> comments) {
        mComments = comments;
    }
}
