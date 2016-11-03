package moe.tlaster.openween.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import moe.tlaster.openween.R;
import moe.tlaster.openween.fragment.user.Follower;
import moe.tlaster.openween.fragment.user.Following;
import moe.tlaster.openween.fragment.user.Timeline;

/**
 * Created by Asahi on 2016/11/3.
 */

public class UserListActivity extends BaseActivity {
    public static final int USER_LIST_TYPE_FOLLOWER = 1;
    public static final int USER_LIST_TYPE_FOLLOWING = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_list);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (getIntent().getExtras().getInt(getString(R.string.user_list_type_name))) {
            case USER_LIST_TYPE_FOLLOWER:
                setTitle("粉丝");
                fragmentTransaction.add(R.id.weibo_list_fragment_container, Follower.create(getIntent().getExtras().getLong(getString(R.string.weibo_list_user_id_name))));
                break;
            case USER_LIST_TYPE_FOLLOWING:
                setTitle("关注");
                fragmentTransaction.add(R.id.weibo_list_fragment_container, Following.create(getIntent().getExtras().getLong(getString(R.string.weibo_list_user_id_name))));
                break;
        }
        fragmentTransaction.commit();
    }
}
