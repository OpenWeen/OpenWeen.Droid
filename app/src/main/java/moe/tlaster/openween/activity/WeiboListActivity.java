package moe.tlaster.openween.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.tlaster.openween.R;
import moe.tlaster.openween.fragment.user.Timeline;

public class WeiboListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_list);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Timeline timeline = Timeline.create(getIntent().getExtras().getLong(getString(R.string.weibo_list_user_id_name)));
        fragmentTransaction.add(R.id.weibo_list_fragment_container, timeline);
        fragmentTransaction.commit();
    }
}
