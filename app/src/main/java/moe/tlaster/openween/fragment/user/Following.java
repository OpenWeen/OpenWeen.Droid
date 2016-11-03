package moe.tlaster.openween.fragment.user;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.IIcon;

import java.util.List;

import moe.tlaster.openween.adapter.UserListAdapter;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.core.api.friendships.Friends;
import moe.tlaster.openween.core.model.user.UserListModel;
import moe.tlaster.openween.core.model.user.UserModel;
import moe.tlaster.openween.fragment.WeiboListBase;
import okhttp3.Call;

/**
 * Created by Asahi on 2016/11/3.
 */

public class Following extends WeiboListBase<UserModel> {


    private long mID;
    private int mCursor;


    public static Following create(long id) {
        Following following = new Following();
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        following.setArguments(bundle);
        return following;
    }

    @Override
    protected BaseQuickAdapter<UserModel> initAdapter() {
        return new UserListAdapter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mID = getArguments().getLong("id");
    }

    @Override
    protected void loadMoreOverride(Callback<List<UserModel>> callback) {
        Friends.getFriends(mID, mLoadCount, mCursor, new JsonCallback<UserListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onError(e);
            }

            @Override
            public void onResponse(UserListModel response, int id) {
                mCursor = Integer.parseInt(response.getNextCursor());
                callback.onResponse(response.getUsers(), response.getTotalNumber());
            }
        });
    }

    @Override
    protected void refreshOverride(Callback<List<UserModel>> callback) {
        mCursor = 0;
        Friends.getFriends(mID, mLoadCount, mCursor, new JsonCallback<UserListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onError(e);
            }

            @Override
            public void onResponse(UserListModel response, int id) {
                mCursor = Integer.parseInt(response.getNextCursor());
                callback.onResponse(response.getUsers(), response.getTotalNumber());
            }
        });
    }

    @Override
    public IIcon getIcon() {
        return GoogleMaterial.Icon.gmd_people;
    }
}
