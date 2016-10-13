package moe.tlaster.openween.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.klinker.android.sliding.SlidingActivity;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import moe.tlaster.openween.R;
import moe.tlaster.openween.adapter.BaseModelAdapter;
import moe.tlaster.openween.common.SimpleDividerItemDecoration;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.core.api.statuses.UserTimeline;
import moe.tlaster.openween.core.api.user.User;
import moe.tlaster.openween.core.model.status.MessageListModel;
import moe.tlaster.openween.core.model.status.MessageModel;
import moe.tlaster.openween.core.model.user.UserModel;
import okhttp3.Call;

/**
 * Created by Asahi on 2016/10/10.
 */

public class UserActivity extends SlidingActivity {
    private UserModel mUser;
    private CircleImageView mCircleImageView;

    @Override
    public void init(Bundle savedInstanceState) {
        setContent(R.layout.activity_user);
        setPrimaryColors(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorPrimaryDark));
        ButterKnife.bind(this);
        View headerView = getLayoutInflater().inflate(R.layout.user_icon, null);
        mCircleImageView = (CircleImageView) headerView.findViewById(R.id.user_img);
        mCircleImageView.setImageDrawable(new IconicsDrawable(this)
                .icon(GoogleMaterial.Icon.gmd_person)
                .color(Color.WHITE)
                .sizeDp(24));
        setHeaderContent(headerView);
        String userName = getIntent().getExtras().getString(getString(R.string.user_page_username_name));
        User.getUser(userName, new JsonCallback<UserModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(UserActivity.this, "载入失败", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(UserModel response, int id) {
                mUser = response;
                initUser();
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
                mCircleImageView.setImageBitmap(response);
            }
        });
    }
}
