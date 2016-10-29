package moe.tlaster.openween.fragment.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.IIcon;

import java.util.List;

import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.core.model.status.MessageModel;
import moe.tlaster.openween.core.model.status.RepostListModel;
import moe.tlaster.openween.core.model.types.AuthorType;
import moe.tlaster.openween.fragment.WeiboListBase;
import okhttp3.Call;

/**
 * Created by Asahi on 2016/10/29.
 */

public class Repost extends WeiboListBase<MessageModel> {

    private long mID;

    public static Repost create(long id) {
        Repost repost = new Repost();
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        repost.setArguments(bundle);
        return repost;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mID = getArguments().getLong("id");
    }

    @Override
    protected void loadMoreOverride(Callback<List<MessageModel>> callback) {
        moe.tlaster.openween.core.api.statuses.Repost.getRepost(mID, 0, mAdapter.getData().get(mAdapter.getData().size() - 1).getID(), mLoadCount, 1, AuthorType.All, new JsonCallback<RepostListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onError(e);
            }

            @Override
            public void onResponse(RepostListModel response, int id) {
                response.getReposts().remove(0);
                callback.onResponse(response.getReposts(), response.getTotalNumber());
            }
        });
    }

    @Override
    protected void refreshOverride(Callback<List<MessageModel>> callback) {
        moe.tlaster.openween.core.api.statuses.Repost.getRepost(mID, super.mLoadCount, 1, new JsonCallback<RepostListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onError(e);
            }

            @Override
            public void onResponse(RepostListModel response, int id) {
                callback.onResponse(response.getReposts(), response.getTotalNumber());
            }
        });
    }

    @Override
    public IIcon getIcon() {
        return GoogleMaterial.Icon.gmd_reply;
    }
}
