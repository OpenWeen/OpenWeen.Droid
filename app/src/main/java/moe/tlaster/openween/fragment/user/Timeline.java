package moe.tlaster.openween.fragment.user;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.IIcon;

import java.util.List;

import moe.tlaster.openween.adapter.BaseModelAdapter;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.core.api.statuses.UserTimeline;
import moe.tlaster.openween.core.model.status.MessageListModel;
import moe.tlaster.openween.core.model.status.MessageModel;
import moe.tlaster.openween.fragment.WeiboListBase;
import moe.tlaster.openween.fragment.detail.Repost;
import okhttp3.Call;

/**
 * Created by Asahi on 2016/11/3.
 */

public class Timeline extends WeiboListBase<MessageModel> {

    private long mID;

    public static Timeline create(long id) {
        Timeline timeline = new Timeline();
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        timeline.setArguments(bundle);
        return timeline;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mID = getArguments().getLong("id");
    }


    @Override
    protected void loadMoreOverride(Callback<List<MessageModel>> callback) {
        UserTimeline.getUserTimeline(mID, mLoadCount, ((MessageModel) mAdapter.getData().get(mAdapter.getData().size() - 1)).getID(), new JsonCallback<MessageListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onError(e);
            }

            @Override
            public void onResponse(MessageListModel response, int id) {
                response.getStatuses().remove(0);
                callback.onResponse(response.getStatuses(), response.getTotalNumber());
            }
        });
    }

    @Override
    protected void refreshOverride(Callback<List<MessageModel>> callback) {
        UserTimeline.getUserTimeline(mID, mLoadCount, new JsonCallback<MessageListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onError(e);
            }

            @Override
            public void onResponse(MessageListModel response, int id) {
                callback.onResponse(response.getStatuses(), response.getTotalNumber());
            }
        });
    }

    @Override
    public IIcon getIcon() {
        return GoogleMaterial.Icon.gmd_home;
    }
}
