package moe.tlaster.openween.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.tlaster.openween.R;
import moe.tlaster.openween.common.StaticResource;
import moe.tlaster.openween.common.controls.Pivot;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.core.api.user.User;
import moe.tlaster.openween.core.model.user.UserModel;
import moe.tlaster.openween.fragment.main.DirectMessage;
import moe.tlaster.openween.fragment.main.Message;
import moe.tlaster.openween.fragment.main.Timeline;
import okhttp3.Call;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_pivot)
    public Pivot mPivot;
    private Pivot.FragmentPageAdapter mPageAdapter;
    private Drawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                ButterKnife.bind(this);
                mDrawer = new DrawerBuilder().withActivity(this).build();
                mPageAdapter = new Pivot.FragmentPageAdapter(this, getSupportFragmentManager());
                mPageAdapter.add(new Timeline());
                mPageAdapter.add(new Message());
                mPageAdapter.add(new DirectMessage());
                mPivot.setAdapter(mPageAdapter);
                mPivot.setOffscreenPageLimit(3);
                User.getUser(StaticResource.getUid(), new JsonCallback<UserModel>() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(UserModel response, int id) {
                        mPivot.setProfileImage(response.getAvatarLarge());
                        new AccountHeaderBuilder()
                        .withActivity(MainActivity.this)
                        .withDrawer(mDrawer)
                        .withHeaderBackground(new com.mikepenz.materialdrawer.holder.ImageHolder(response.getCoverimage()))
                        .addProfiles(new ProfileDrawerItem().withNameShown(true).withName(response.getScreenName()).withEmail(response.getDescription()).withIcon(response.getAvatarLarge()))
                        .withOnAccountHeaderListener((view, profile, currentProfile) -> false)
                        .build();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageDrawable(new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_add).color(Color.WHITE).sizeDp(24));
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }
}
