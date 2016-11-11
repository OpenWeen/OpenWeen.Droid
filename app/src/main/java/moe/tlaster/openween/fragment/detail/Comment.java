package moe.tlaster.openween.fragment.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.IIcon;

import java.util.List;

import moe.tlaster.openween.adapter.BaseModelAdapter;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.core.api.comments.Comments;
import moe.tlaster.openween.core.model.comment.CommentListModel;
import moe.tlaster.openween.core.model.comment.CommentModel;
import moe.tlaster.openween.core.model.types.AuthorType;
import moe.tlaster.openween.fragment.WeiboListBase;
import okhttp3.Call;

/**
 * Created by Asahi on 2016/10/29.
 */

public class Comment extends WeiboListBase<CommentModel> {

    private long mID;

    public static Comment create(long id) {
        Comment comment = new Comment();
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        comment.setArguments(bundle);
        return comment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mID = getArguments().getLong("id");
    }

    @Override
    protected BaseQuickAdapter<CommentModel, BaseViewHolder> initAdapter() {
        return new BaseModelAdapter<>(false);
    }

    @Override
    protected void loadMoreOverride(Callback<List<CommentModel>> callback) {
        Comments.getCommentStatus(mID, 0, ((CommentModel) mAdapter.getData().get(mAdapter.getData().size() - 1)).getID(), mLoadCount, 1, AuthorType.All, new JsonCallback<CommentListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onError(e);
            }

            @Override
            public void onResponse(CommentListModel response, int id) {
                response.getComments().remove(0);
                callback.onResponse(response.getComments(), response.getTotalNumber());
            }
        });
    }

    @Override
    protected void refreshOverride(Callback<List<CommentModel>> callback) {
        Comments.getCommentStatus(mID, mLoadCount, 1, new JsonCallback<CommentListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onError(e);
            }

            @Override
            public void onResponse(CommentListModel response, int id) {
                callback.onResponse(response.getComments(), response.getTotalNumber());
            }
        });
    }

    @Override
    public IIcon getIcon() {
        return GoogleMaterial.Icon.gmd_comment;
    }
}
