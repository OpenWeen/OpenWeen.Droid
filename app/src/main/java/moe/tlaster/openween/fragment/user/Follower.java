package moe.tlaster.openween.fragment.user;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.IIcon;

import java.util.List;

import moe.tlaster.openween.adapter.UserListAdapter;
import moe.tlaster.openween.common.StaticResource;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.core.api.friendships.Friends;
import moe.tlaster.openween.core.api.remind.Remind;
import moe.tlaster.openween.core.model.types.RemindType;
import moe.tlaster.openween.core.model.user.UserListModel;
import moe.tlaster.openween.core.model.user.UserModel;
import moe.tlaster.openween.fragment.WeiboListBase;
import okhttp3.Call;

/**
 * Created by Asahi on 2016/11/3.
 */

public class Follower extends WeiboListBase<UserModel> {

    private long mID;
    private int mCursor;


    public static Follower create(long id) {
        Follower follower = new Follower();
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        follower.setArguments(bundle);
        return follower;
    }

    @Override
    protected BaseQuickAdapter<UserModel, BaseViewHolder> initAdapter() {
        return new UserListAdapter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mID = getArguments().getLong("id");
    }

    @Override
    protected void loadMoreOverride(Callback<List<UserModel>> callback) {
        Friends.getFollowers(mID, mLoadCount, mCursor, new JsonCallback<UserListModel>() {
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
        if (mID == StaticResource.getUid())
            Remind.clearUnread(RemindType.Follower);
        mCursor = 0;
        Friends.getFollowers(mID, mLoadCount, mCursor, new JsonCallback<UserListModel>() {
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
