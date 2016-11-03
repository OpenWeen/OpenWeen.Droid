package moe.tlaster.openween.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.jorgecastilloprz.FABProgressCircle;
import com.klinker.android.sliding.SlidingActivity;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.transitionseverywhere.AutoTransition;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import moe.tlaster.openween.R;
import moe.tlaster.openween.adapter.BaseModelAdapter;
import moe.tlaster.openween.common.SimpleDividerItemDecoration;
import moe.tlaster.openween.common.StaticResource;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.common.helpers.WeiboCardHelper;
import moe.tlaster.openween.core.api.friendships.Friends;
import moe.tlaster.openween.core.api.statuses.UserTimeline;
import moe.tlaster.openween.core.api.user.User;
import moe.tlaster.openween.core.model.status.MessageListModel;
import moe.tlaster.openween.core.model.status.MessageModel;
import moe.tlaster.openween.core.model.user.UserListModel;
import moe.tlaster.openween.core.model.user.UserModel;
import okhttp3.Call;

/**
 * Created by Asahi on 2016/10/10.
 */

public class UserActivity extends SlidingActivity {
    private UserModel mUser;
    private CircleImageView mCircleImageView;
    @BindView(R.id.user_stats_card)
    public View mStatsCard;
    @BindView(R.id.user_weibo_card)
    public View mWeiboCard;
    @BindView(R.id.user_progressbar)
    public ProgressBar mProgressBar;
    @BindView(R.id.user_information)
    public LinearLayout mLinearLayout;

    @Override
    public void init(Bundle savedInstanceState) {
        setContent(R.layout.activity_user);
        setPrimaryColors(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorPrimaryDark));
        ButterKnife.bind(this);
        mProgressBar.getIndeterminateDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        View headerView = getLayoutInflater().inflate(R.layout.user_icon, null);
        mCircleImageView = (CircleImageView) headerView.findViewById(R.id.user_img);
        mCircleImageView.setImageDrawable(new IconicsDrawable(this)
                .icon(GoogleMaterial.Icon.gmd_person)
                .color(Color.WHITE)
                .sizeDp(24));
        setHeaderContent(headerView);
        String userName = getIntent().getExtras().getString(getString(R.string.user_page_username_name));
        mWeiboCard.findViewById(R.id.user_weibo_all).setOnClickListener(this::goAllWeiboList);
        mWeiboCard.findViewById(R.id.user_weibo_all_bottom).setOnClickListener(this::goAllWeiboList);
        User.getUser(userName, new JsonCallback<UserModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(UserActivity.this, "载入失败", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(UserModel response, int id) {
                if (isDestroyed()) return;
                mUser = response;
                initUser();
                initWeibo();
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void goAllWeiboList(View view) {
        Intent intent = new Intent(this, WeiboListActivity.class);
        intent.putExtra(getString(R.string.weibo_list_user_id_name), mUser.getID());
        startActivity(intent);
    }

    private void initWeibo() {
        UserTimeline.getUserTimeline(mUser.getID(), 3, 1, new JsonCallback<MessageListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(MessageListModel response, int id) {
                if (isDestroyed()) return;
                if (response.getStatuses().size() > 0) {
                    WeiboCardHelper.setData(mWeiboCard.findViewById(R.id.user_weibo_1), response.getStatuses().get(0), UserActivity.this, true);
                    TransitionManager.beginDelayedTransition(mLinearLayout, new Slide(Gravity.BOTTOM));
                    mWeiboCard.setVisibility(View.VISIBLE);
                }
                if (response.getStatuses().size() > 1)
                    WeiboCardHelper.setData(mWeiboCard.findViewById(R.id.user_weibo_2), response.getStatuses().get(1), UserActivity.this, true);
                if (response.getStatuses().size() > 2)
                    WeiboCardHelper.setData(mWeiboCard.findViewById(R.id.user_weibo_3), response.getStatuses().get(2), UserActivity.this, true);
            }
        });
    }

    private void initUser() {
        setTitle(mUser.getScreenName());
        if (!TextUtils.isEmpty(mUser.getCoverimage()))
            OkHttpUtils.get().url(mUser.getCoverimage()).build().execute(new BitmapCallback()
            {
                @Override
                public void onError(Call call, Exception e, int id) {

                }
                @Override
                public void onResponse(Bitmap response, int id) {
                    if (!isDestroyed())
                        setImage(response);
                }
            });
        OkHttpUtils.get().url(mUser.getAvatarLarge()).build().execute(new BitmapCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(Bitmap response, int id) {
                if (isDestroyed()) return;
                mCircleImageView.setImageBitmap(response);
            }
        });
        TransitionManager.beginDelayedTransition(mLinearLayout, new Slide(Gravity.BOTTOM));
        mStatsCard.setVisibility(View.VISIBLE);
        mStatsCard.findViewById(R.id.user_stats_following_layout).setOnClickListener(view -> {
            Intent intent = new Intent(this, UserListActivity.class);
            intent.putExtra(getString(R.string.weibo_list_user_id_name), mUser.getID());
            intent.putExtra(getString(R.string.user_list_type_name), UserListActivity.USER_LIST_TYPE_FOLLOWING);
            startActivity(intent);
        });
        mStatsCard.findViewById(R.id.user_stats_follower_layout).setOnClickListener(view -> {
            Intent intent = new Intent(this, UserListActivity.class);
            intent.putExtra(getString(R.string.weibo_list_user_id_name), mUser.getID());
            intent.putExtra(getString(R.string.user_list_type_name), UserListActivity.USER_LIST_TYPE_FOLLOWER);
            startActivity(intent);
        });
        ((TextView) mStatsCard.findViewById(R.id.user_stats_user_des)).setText(mUser.getDescription());
        ((TextView) mStatsCard.findViewById(R.id.user_stats_follower_count)).setText(String.valueOf(mUser.getFollowersCount()));
        ((TextView) mStatsCard.findViewById(R.id.user_stats_following_count)).setText(String.valueOf(mUser.getFriendsCount()));
        if (!TextUtils.isEmpty(mUser.getUrl())) ((TextView) mStatsCard.findViewById(R.id.user_stats_url)).setText(mUser.getUrl());
        else mStatsCard.findViewById(R.id.user_stats_url_box).setVisibility(View.GONE);
        if (!TextUtils.isEmpty(mUser.getLocation())) ((TextView) mStatsCard.findViewById(R.id.user_stats_location)).setText(mUser.getLocation());
        else mStatsCard.findViewById(R.id.user_stats_location_box).setVisibility(View.GONE);
        if (!TextUtils.isEmpty(mUser.getVerifiedReason())) ((TextView) mStatsCard.findViewById(R.id.user_verified_reason)).setText(mUser.getVerifiedReason());
        else mStatsCard.findViewById(R.id.user_stats_verified_box).setVisibility(View.GONE);
        ((TextView) mStatsCard.findViewById(R.id.user_stats_created_time)).setText(mUser.getCreatedAtDiffForHuman());
        if (mUser.isFollowing() && mUser.isFollowMe()) ((Button) mStatsCard.findViewById(R.id.user_stats_follow_state)).setText("互相关注");
        else if (mUser.isFollowMe()) ((Button) mStatsCard.findViewById(R.id.user_stats_follow_state)).setText("被关注");
        else if (mUser.isFollowing()) ((Button) mStatsCard.findViewById(R.id.user_stats_follow_state)).setText("正在关注");
        else if (mUser.getID() == StaticResource.getUid()) mStatsCard.findViewById(R.id.user_stats_follow_state).setVisibility(View.INVISIBLE);
        else ((Button) mStatsCard.findViewById(R.id.user_stats_follow_state)).setText("关注");
        Friends.getFollowers(mUser.getID(), 3, 0, new JsonCallback<UserListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(UserListModel response, int id) {
                if (isDestroyed()) return;
                if (response.getUsers().size() > 0)
                    Glide.with(UserActivity.this).load(response.getUsers().get(0).getAvatarLarge()).fitCenter().crossFade().into(((CircleImageView) mStatsCard.findViewById(R.id.user_stats_follower_icon_1)));
                if (response.getUsers().size() > 1)
                    Glide.with(UserActivity.this).load(response.getUsers().get(1).getAvatarLarge()).fitCenter().crossFade().into(((CircleImageView) mStatsCard.findViewById(R.id.user_stats_follower_icon_2)));
                if (response.getUsers().size() > 2)
                    Glide.with(UserActivity.this).load(response.getUsers().get(2).getAvatarLarge()).fitCenter().crossFade().into(((CircleImageView) mStatsCard.findViewById(R.id.user_stats_follower_icon_3)));
            }
        });
        Friends.getFriends(mUser.getID(), 3, 0, new JsonCallback<UserListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(UserListModel response, int id) {
                if (isDestroyed()) return;
                if (response.getUsers().size() > 0)
                    Glide.with(UserActivity.this).load(response.getUsers().get(0).getAvatarLarge()).fitCenter().crossFade().into(((CircleImageView) mStatsCard.findViewById(R.id.user_stats_following_icon_1)));
                if (response.getUsers().size() > 1)
                    Glide.with(UserActivity.this).load(response.getUsers().get(1).getAvatarLarge()).fitCenter().crossFade().into(((CircleImageView) mStatsCard.findViewById(R.id.user_stats_following_icon_2)));
                if (response.getUsers().size() > 2)
                    Glide.with(UserActivity.this).load(response.getUsers().get(2).getAvatarLarge()).fitCenter().crossFade().into(((CircleImageView) mStatsCard.findViewById(R.id.user_stats_following_icon_3)));
            }
        });
    }
}
