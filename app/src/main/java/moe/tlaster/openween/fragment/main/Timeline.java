package moe.tlaster.openween.fragment.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.IIcon;

import java.util.List;

import moe.tlaster.openween.R;
import moe.tlaster.openween.adapter.BaseModelAdapter;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.core.api.statuses.Home;
import moe.tlaster.openween.core.model.status.MessageListModel;
import moe.tlaster.openween.core.model.status.MessageModel;
import moe.tlaster.openween.fragment.WeiboListBase;
import okhttp3.Call;

/**
 * Created by Tlaster on 2016/9/9.
 */
public class Timeline extends WeiboListBase<MessageModel> {

    @Override
    protected void loadMoreOverride(Callback<List<MessageModel>> callback) {
        Home.getTimeline(mLoadCount, mAdapter.getData().get(mAdapter.getData().size() - 1).getID(), new JsonCallback<MessageListModel>() {
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
        Home.getTimeline(mLoadCount, new JsonCallback<MessageListModel>() {
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
