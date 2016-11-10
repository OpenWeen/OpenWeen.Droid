package moe.tlaster.openween.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.transition.Slide;
import android.view.Gravity;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.ImageHolder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.tlaster.openween.R;
import moe.tlaster.openween.common.controls.Pivot;
import moe.tlaster.openween.common.entities.PostWeiboType;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.common.helpers.WeiboCardHelper;
import moe.tlaster.openween.core.api.friendships.Groups;
import moe.tlaster.openween.core.model.status.GroupListModel;
import moe.tlaster.openween.core.model.status.GroupModel;
import moe.tlaster.openween.core.model.user.UserModel;
import moe.tlaster.openween.fragment.main.DirectMessage;
import moe.tlaster.openween.fragment.main.Message;
import moe.tlaster.openween.fragment.main.Timeline;
import okhttp3.Call;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_pivot)
    public Pivot mPivot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!getIntent().hasExtra("user")) {
            goSplash();
            return;
        }
        UserModel user = getIntent().getExtras().getParcelable("user");
        //setupWindowAnimations();
        ButterKnife.bind(this);
        Timeline timeline = new Timeline();
        PrimaryDrawerItem drawerItemHome;
        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .addDrawerItems(
                        drawerItemHome = new PrimaryDrawerItem().withIdentifier(1).withName("首页").withIcon(GoogleMaterial.Icon.gmd_home).withOnDrawerItemClickListener((view, position, drawerItem) -> {
                            timeline.toGroup(-1);
                            return false;
                        }),
                        new DividerDrawerItem(),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withIdentifier(2).withName("设置").withIcon(GoogleMaterial.Icon.gmd_settings).withOnDrawerItemClickListener((view, position, drawerItem) -> {
                            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                            return false;
                        })
                )
                .withCloseOnClick(true)
                .build();
        drawer.setSelection(drawerItemHome, false);
        Pivot.FragmentPageAdapter pageAdapter = new Pivot.FragmentPageAdapter(this, getSupportFragmentManager());
        pageAdapter.add(timeline);
        pageAdapter.add(new Message());
        pageAdapter.add(new DirectMessage());
        mPivot.setAdapter(pageAdapter);
        mPivot.setOffscreenPageLimit(3);
        mPivot.setProfileImage(user.getAvatarLarge());
        mPivot.setProfileImageOnClickListener(view -> WeiboCardHelper.goUserActivity(user.getScreenName(), MainActivity.this));
        AccountHeaderBuilder accountHeaderBuilder = new AccountHeaderBuilder()
                .withActivity(MainActivity.this)
                .withDrawer(drawer)
                .addProfiles(new ProfileDrawerItem().withNameShown(true).withName(user.getScreenName()).withEmail(user.getDescription()).withIcon(user.getAvatarLarge()))
                .withOnAccountHeaderListener((view, profile, currentProfile) -> false);
        if (!TextUtils.isEmpty(user.getCoverimage()))
            accountHeaderBuilder.withHeaderBackground(new ImageHolder(user.getCoverimage()));
        else
            accountHeaderBuilder.withHeaderBackground(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        accountHeaderBuilder.build();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.main_fab);
        fab.setImageDrawable(new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_add).color(Color.WHITE).sizeDp(24));
        fab.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, PostWeiboActivity.class);
            i.putExtra(getString(R.string.post_weibo_type_name), PostWeiboType.NewPost);
            startActivity(i, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
        });
        Groups.getGroups(new JsonCallback<GroupListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(GroupListModel response, int id) {
                for (int i = response.getLists().size() - 1; i >= 0; i--) {
                    GroupModel item = response.getLists().get(i);
                    drawer.addItemAtPosition(new PrimaryDrawerItem().withIdentifier(item.getID()).withName(item.getName()).withOnDrawerItemClickListener(((view, position, drawerItem) -> {
                        timeline.toGroup(item.getID());
                        return false;
                    })), 3);
                }
            }
        });
    }

    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setDuration(500);
        slide.setSlideEdge(Gravity.TOP);
        getWindow().setEnterTransition(slide);
    }
}
