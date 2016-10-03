package moe.tlaster.openween.fragment.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.IIcon;

import java.util.ArrayList;
import java.util.List;
import com.annimon.stream.*;

import moe.tlaster.openween.adapter.BaseModelAdapter;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.common.helpers.Reverse;
import moe.tlaster.openween.core.api.comments.Comments;
import moe.tlaster.openween.core.api.remind.Remind;
import moe.tlaster.openween.core.api.statuses.Mentions;
import moe.tlaster.openween.core.model.BaseModel;
import moe.tlaster.openween.core.model.comment.CommentListModel;
import moe.tlaster.openween.core.model.status.MessageListModel;
import moe.tlaster.openween.core.model.types.RemindType;
import moe.tlaster.openween.fragment.WeiboListBase;
import okhttp3.Call;
/**
 * Created by Tlaster on 2016/9/9.
 */
public class Message extends WeiboListBase<BaseModel> {

    private long mMentionID;
    private long mCommentID;
    private long mCommentMentionsID;

    @Override
    protected BaseQuickAdapter initAdapter() {
        return new BaseModelAdapter();
    }

    @Override
    protected RecyclerView.OnItemTouchListener itemTouch() {
        return new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                childClick(view.getId());
            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                childClick(view.getId());
            }

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                itemClick();
            }
        };
    }

    private void itemClick() {

    }

    private void childClick(int id) {

    }

    @Override
    protected void refreshOverride(Callback<List<BaseModel>> callback) { //Fucking JAVA
        final List<BaseModel> mention = new ArrayList<>();
        final int[] mentionNumber = {0};
        final List<BaseModel> comment = new ArrayList<>();
        final int[] commentNumber = {0};
        final List<BaseModel> commentMentions = new ArrayList<>();
        final int[] commentMentionsNumber = {0};
        Remind.clearUnread(RemindType.MentionStatus);
        Mentions.getMentions(mLoadCount, new JsonCallback<MessageListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }
            @Override
            public void onResponse(MessageListModel response, int id) {
                mention.addAll(response.getStatuses());
                mentionNumber[0] += response.getTotalNumber();
                mMentionID = response.getStatuses().get(response.getStatuses().size() - 1).getID();
                Remind.clearUnread(RemindType.Comment);
                Comments.getCommentToMe(mLoadCount, new JsonCallback<CommentListModel>() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(CommentListModel response, int id) {
                        comment.addAll(response.getComments());
                        commentNumber[0] = response.getTotalNumber();
                        mCommentID = response.getComments().get(response.getComments().size() - 1).getID();
                        Remind.clearUnread(RemindType.MentionComment);
                        Comments.getCommentMentions(mLoadCount, new JsonCallback<CommentListModel>() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }
                            @Override
                            public void onResponse(CommentListModel response, int id) {
                                commentMentions.addAll(response.getComments());
                                commentMentionsNumber[0] = response.getTotalNumber();
                                mCommentMentionsID = response.getComments().get(response.getComments().size() - 1).getID();
                                callback.onResponse(Stream.concat(Stream.concat(Stream.of(mention), Stream.of(comment)), Stream.of(commentMentions))
                                                .sortBy(BaseModel::getCreatedDate)
                                                .custom(new Reverse<>()).collect(Collectors.toList()),
                                        mentionNumber[0] + commentNumber[0] + commentMentionsNumber[0]);
                            }
                        });
                    }
                });
            }
        });

    }

    @Override
    protected void loadMoreOverride(Callback<List<BaseModel>> callback) {

        final List<BaseModel> mention = new ArrayList<>();
        final int[] mentionNumber = {0};
        final List<BaseModel> comment = new ArrayList<>();
        final int[] commentNumber = {0};
        final List<BaseModel> commentMentions = new ArrayList<>();
        final int[] commentMentionsNumber = {0};
        Mentions.getMentions(mMentionID, mLoadCount, new JsonCallback<MessageListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(MessageListModel response, int id) {
                mention.addAll(response.getStatuses());
                mentionNumber[0] += response.getTotalNumber();
                mMentionID = response.getStatuses().get(response.getStatuses().size() - 1).getID();
            }
        });
        Comments.getCommentToMe(mCommentID, mLoadCount, new JsonCallback<CommentListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(CommentListModel response, int id) {
                comment.addAll(response.getComments());
                commentNumber[0] = response.getTotalNumber();
                mCommentID = response.getComments().get(response.getComments().size() - 1).getID();
            }
        });
        Comments.getCommentMentions(mCommentMentionsID, mLoadCount, new JsonCallback<CommentListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(CommentListModel response, int id) {
                commentMentions.addAll(response.getComments());
                commentMentionsNumber[0] = response.getTotalNumber();
                mCommentMentionsID = response.getComments().get(response.getComments().size() - 1).getID();
            }
        });
        callback.onResponse(Stream.concat(Stream.concat(Stream.of(mention), Stream.of(comment)), Stream.of(commentMentions))
                        .sortBy(BaseModel::getCreatedDate)
                        .custom(new Reverse<>()).collect(Collectors.toList()),
                mentionNumber[0] + commentNumber[0] + commentMentionsNumber[0]);
    }

    @Override
    public IIcon getIcon() {
        return GoogleMaterial.Icon.gmd_forum;
    }
}
