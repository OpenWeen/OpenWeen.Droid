package moe.tlaster.openween;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.DrawerBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.tlaster.openween.common.StaticResource;
import moe.tlaster.openween.common.controls.Pivot;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.core.api.user.User;
import moe.tlaster.openween.core.model.user.UserModel;
import moe.tlaster.openween.fragment.main.DirectMessage;
import moe.tlaster.openween.fragment.main.Message;
import moe.tlaster.openween.fragment.main.Timeline;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_pivot)
    public Pivot mPivot;
    private Pivot.FragmentPageAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPageAdapter = new Pivot.FragmentPageAdapter(this, getSupportFragmentManager());
        mPageAdapter.add(new Timeline());
        mPageAdapter.add(new Message());
        mPageAdapter.add(new DirectMessage());
        mPivot.setAdapter(mPageAdapter);
        mPivot.setOffscreenPageLimit(3);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        try {
            User.getUser(StaticResource.getUid(), new JsonCallback<UserModel>() {
                @Override
                public void onError(Call call, Exception e, int id) {
                }

                @Override
                public void onResponse(UserModel response, int id) {
                    mPivot.setProfileImage(response.getProfileImageUrl());
                }
            });
        } catch (InvalidAccessTokenException e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageDrawable(new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_add).color(Color.WHITE).sizeDp(24));
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        new DrawerBuilder().withActivity(this).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
