package moe.tlaster.openween.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.tlaster.openween.R;
import moe.tlaster.openween.common.StaticResource;
import moe.tlaster.openween.common.controls.Pivot;
import moe.tlaster.openween.common.entities.PostWeiboType;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.common.helpers.WeiboCardHelper;
import moe.tlaster.openween.core.api.friendships.Groups;
import moe.tlaster.openween.core.api.statuses.PostWeibo;
import moe.tlaster.openween.core.api.user.User;
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
        //setupWindowAnimations();
        ButterKnife.bind(this);
        Timeline timeline = new Timeline();
        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .addDrawerItems(
                        new PrimaryDrawerItem().withIdentifier(1).withName("首页").withIcon(GoogleMaterial.Icon.gmd_home).withOnDrawerItemClickListener((view, position, drawerItem) -> {
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
        drawer.setSelection(-1);
        Pivot.FragmentPageAdapter pageAdapter = new Pivot.FragmentPageAdapter(this, getSupportFragmentManager());
        pageAdapter.add(timeline);
        pageAdapter.add(new Message());
        pageAdapter.add(new DirectMessage());
        mPivot.setAdapter(pageAdapter);
        mPivot.setOffscreenPageLimit(3);
        UserModel user = getIntent().getExtras().getParcelable("user");
        mPivot.setProfileImage(user.getAvatarLarge());
        mPivot.setProfileImageOnClickListener(view -> WeiboCardHelper.goUserActivity(user.getScreenName(), MainActivity.this));
        new AccountHeaderBuilder()
                .withActivity(MainActivity.this)
                .withDrawer(drawer)
                .withHeaderBackground(new com.mikepenz.materialdrawer.holder.ImageHolder(user.getCoverimage()))
                .addProfiles(new ProfileDrawerItem().withNameShown(true).withName(user.getScreenName()).withEmail(user.getDescription()).withIcon(user.getAvatarLarge()))
                .withOnAccountHeaderListener((view, profile, currentProfile) -> false)
                .build();
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
