package moe.tlaster.openween.activity

import android.os.Bundle
import android.support.v4.app.FragmentTransaction

import moe.tlaster.openween.R
import moe.tlaster.openween.fragment.user.Follower
import moe.tlaster.openween.fragment.user.Following
import moe.tlaster.openween.fragment.user.Timeline

/**
 * Created by Asahi on 2016/11/3.
 */

class UserListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weibo_list)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        when (intent.extras.getInt(getString(R.string.user_list_type_name))) {
            USER_LIST_TYPE_FOLLOWER -> {
                title = "粉丝"
                fragmentTransaction.add(R.id.weibo_list_fragment_container, Follower.create(intent.extras.getLong(getString(R.string.weibo_list_user_id_name))))
            }
            USER_LIST_TYPE_FOLLOWING -> {
                title = "关注"
                fragmentTransaction.add(R.id.weibo_list_fragment_container, Following.create(intent.extras.getLong(getString(R.string.weibo_list_user_id_name))))
            }
        }
        fragmentTransaction.commit()
    }

    companion object {
        val USER_LIST_TYPE_FOLLOWER = 1
        val USER_LIST_TYPE_FOLLOWING = 2
    }
}
