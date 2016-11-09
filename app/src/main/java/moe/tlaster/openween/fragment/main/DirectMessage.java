package moe.tlaster.openween.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.IIcon;

import java.util.List;

import moe.tlaster.openween.R;
import moe.tlaster.openween.adapter.DirectMessageUserListAdapter;
import moe.tlaster.openween.common.controls.Pivot;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.core.model.directmessage.DirectMessageUserListModel;
import moe.tlaster.openween.core.model.directmessage.DirectMessageUserModel;
import moe.tlaster.openween.fragment.WeiboListBase;
import okhttp3.Call;

/**
 * Created by Tlaster on 2016/9/9.
 */
public class DirectMessage extends WeiboListBase<DirectMessageUserModel> {


    private int mCursor;

    @Override
    protected BaseQuickAdapter<DirectMessageUserModel> initAdapter() {
        return new DirectMessageUserListAdapter();
    }

    @Override
    protected void loadMoreOverride(Callback<List<DirectMessageUserModel>> callback) {
        moe.tlaster.openween.core.api.directMessage.DirectMessage.getUserList(mLoadCount, mCursor, new JsonCallback<DirectMessageUserListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onError(e);
            }

            @Override
            public void onResponse(DirectMessageUserListModel response, int id) {
                mCursor = Integer.parseInt(response.getNextCursor());
                callback.onResponse(response.getUserList(), response.getTotalNumber());
            }
        });
    }

    @Override
    protected void refreshOverride(Callback<List<DirectMessageUserModel>> callback) {
        mCursor = 0;
        moe.tlaster.openween.core.api.directMessage.DirectMessage.getUserList(mLoadCount, mCursor, new JsonCallback<DirectMessageUserListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onError(e);
            }

            @Override
            public void onResponse(DirectMessageUserListModel response, int id) {
                mCursor = Integer.parseInt(response.getNextCursor());
                callback.onResponse(response.getUserList(), response.getTotalNumber());
            }
        });
    }


    @Override
    public IIcon getIcon() {
        return GoogleMaterial.Icon.gmd_email;
    }
}
