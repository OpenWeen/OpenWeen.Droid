package moe.tlaster.openween.activity

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import com.bumptech.glide.Glide
import com.github.jorgecastilloprz.FABProgressCircle
import com.klinker.android.sliding.SlidingActivity
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.IconicsDrawable
import com.transitionseverywhere.AutoTransition
import com.transitionseverywhere.Slide
import com.transitionseverywhere.TransitionManager
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.BitmapCallback

import java.net.URL
import java.util.ArrayList
import java.util.Arrays

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.bindView
import de.hdodenhof.circleimageview.CircleImageView
import moe.tlaster.openween.R
import moe.tlaster.openween.adapter.BaseModelAdapter
import moe.tlaster.openween.common.SimpleDividerItemDecoration
import moe.tlaster.openween.common.StaticResource
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.common.helpers.WeiboCardHelper
import moe.tlaster.openween.core.api.blocks.Blocks
import moe.tlaster.openween.core.api.friendships.Friends
import moe.tlaster.openween.core.api.statuses.UserTimeline
import moe.tlaster.openween.core.api.user.User
import moe.tlaster.openween.core.model.status.MessageListModel
import moe.tlaster.openween.core.model.status.MessageModel
import moe.tlaster.openween.core.model.user.UserListModel
import moe.tlaster.openween.core.model.user.UserModel
import okhttp3.Call

/**
 * Created by Asahi on 2016/10/10.
 */

class UserActivity : SlidingActivity() {
    private var mUser: UserModel? = null
    private var mCircleImageView: CircleImageView? = null
    val mStatsCard: View by bindView(R.id.user_stats_card)
    val mWeiboCard: View by bindView(R.id.user_weibo_card)
    val mProgressBar: ProgressBar by bindView(R.id.user_progressbar)
    val mLinearLayout: LinearLayout by bindView(R.id.user_information)
    private var mMenu: Menu? = null

    override fun init(savedInstanceState: Bundle?) {
        setContent(R.layout.activity_user)
        setPrimaryColors(resources.getColor(R.color.colorPrimary), resources.getColor(R.color.colorPrimaryDark))
        mProgressBar.indeterminateDrawable.setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN)
        val headerView = layoutInflater.inflate(R.layout.user_icon, null)
        mCircleImageView = headerView.findViewById(R.id.user_img) as CircleImageView
        mCircleImageView!!.setImageDrawable(IconicsDrawable(this)
                .icon(GoogleMaterial.Icon.gmd_person)
                .color(Color.WHITE)
                .sizeDp(24))
        setHeaderContent(headerView)
        val userName = intent.extras.getString(getString(R.string.user_page_username_name))
        mWeiboCard.findViewById(R.id.user_weibo_all).setOnClickListener { this.goAllWeiboList(it) }
        mWeiboCard.findViewById(R.id.user_weibo_all_bottom).setOnClickListener{ this.goAllWeiboList(it) }
        User.getUser(userName, object : JsonCallback<UserModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                Toast.makeText(this@UserActivity, "载入失败", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(response: UserModel?, id: Int) {
                if (isDestroyed) return
                if (response == null) {
                    Toast.makeText(this@UserActivity, "该用户不存在", Toast.LENGTH_SHORT).show()
                    return
                }
                mUser = response
                initMenu()
                initUser()
                initWeibo()
                mProgressBar.visibility = View.GONE
            }
        })
    }

    private fun initMenu() {
        if (mUser!!.id === StaticResource.uid) {
            mMenu!!.clear()
            return
        }
        val blockUserid = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.block_userid_key), null)
        if (TextUtils.isEmpty(blockUserid)) return
        if (Arrays.asList(*blockUserid!!.split(",".toRegex()).dropLastWhile(String::isEmpty).toTypedArray()).contains(mUser!!.id.toString())) {
            mMenu!!.findItem(R.id.menu_user_block).title = "已屏蔽"
        } else {
            mMenu!!.findItem(R.id.menu_user_block).title = "屏蔽"
        }
    }

    private fun goAllWeiboList(view: View) {
        val intent = Intent(this, WeiboListActivity::class.java)
        intent.putExtra(getString(R.string.weibo_list_user_id_name), mUser!!.id)
        startActivity(intent)
    }

    private fun initWeibo() {
        UserTimeline.getUserTimeline(mUser!!.id, count = 3, callback = object : JsonCallback<MessageListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {

            }

            override fun onResponse(response: MessageListModel, id: Int) {
                if (isDestroyed) return
                if (response.statuses!!.isNotEmpty()) {
                    WeiboCardHelper.setData(mWeiboCard.findViewById(R.id.user_weibo_1), response.statuses!![0], this@UserActivity, true)
                    TransitionManager.beginDelayedTransition(mLinearLayout, Slide(Gravity.BOTTOM))
                    mWeiboCard.visibility = View.VISIBLE
                } else
                    mWeiboCard.visibility = View.GONE
                if (response.statuses!!.size > 1)
                    WeiboCardHelper.setData(mWeiboCard.findViewById(R.id.user_weibo_2), response.statuses!![1], this@UserActivity, true)
                else
                    mWeiboCard.findViewById(R.id.user_weibo_2).visibility = View.GONE
                if (response.statuses!!.size > 2)
                    WeiboCardHelper.setData(mWeiboCard.findViewById(R.id.user_weibo_3), response.statuses!![2], this@UserActivity, true)
                else
                    mWeiboCard.findViewById(R.id.user_weibo_3).visibility = View.GONE
            }
        })
    }

    private fun initUser() {
        title = mUser!!.screenName!!
        if (!TextUtils.isEmpty(mUser!!.coverimage))
            OkHttpUtils.get().url(mUser!!.coverimage).build().execute(object : BitmapCallback() {
                override fun onError(call: Call, e: Exception, id: Int) {

                }

                override fun onResponse(response: Bitmap, id: Int) {
                    if (!isDestroyed)
                        setImage(response)
                }
            })
        OkHttpUtils.get().url(mUser!!.avatarLarge).build().execute(object : BitmapCallback() {
            override fun onError(call: Call, e: Exception, id: Int) {
            }

            override fun onResponse(response: Bitmap, id: Int) {
                if (isDestroyed) return
                mCircleImageView!!.setImageBitmap(response)
            }
        })
        TransitionManager.beginDelayedTransition(mLinearLayout, Slide(Gravity.BOTTOM))
        mStatsCard.visibility = View.VISIBLE
        mStatsCard.findViewById(R.id.user_stats_following_layout).setOnClickListener { view ->
            val intent = Intent(this, UserListActivity::class.java)
            intent.putExtra(getString(R.string.weibo_list_user_id_name), mUser!!.id)
            intent.putExtra(getString(R.string.user_list_type_name), UserListActivity.USER_LIST_TYPE_FOLLOWING)
            startActivity(intent)
        }
        mStatsCard.findViewById(R.id.user_stats_follower_layout).setOnClickListener { view ->
            val intent = Intent(this, UserListActivity::class.java)
            intent.putExtra(getString(R.string.weibo_list_user_id_name), mUser!!.id)
            intent.putExtra(getString(R.string.user_list_type_name), UserListActivity.USER_LIST_TYPE_FOLLOWER)
            startActivity(intent)
        }
        (mStatsCard.findViewById(R.id.user_stats_user_des) as TextView).text = mUser!!.description
        (mStatsCard.findViewById(R.id.user_stats_follower_count) as TextView).text = mUser!!.followersCount.toString()
        (mStatsCard.findViewById(R.id.user_stats_following_count) as TextView).text = mUser!!.friendsCount.toString()
        if (!TextUtils.isEmpty(mUser!!.url))
            (mStatsCard.findViewById(R.id.user_stats_url) as TextView).text = mUser!!.url
        else
            mStatsCard.findViewById(R.id.user_stats_url_box).visibility = View.GONE
        if (!TextUtils.isEmpty(mUser!!.location))
            (mStatsCard.findViewById(R.id.user_stats_location) as TextView).text = mUser!!.location
        else
            mStatsCard.findViewById(R.id.user_stats_location_box).visibility = View.GONE
        if (!TextUtils.isEmpty(mUser!!.verifiedReason))
            (mStatsCard.findViewById(R.id.user_verified_reason) as TextView).text = mUser!!.verifiedReason
        else
            mStatsCard.findViewById(R.id.user_stats_verified_box).visibility = View.GONE
        (mStatsCard.findViewById(R.id.user_stats_created_time) as TextView).text = mUser!!.createdAtDiffForHuman
        checkForFollowState()
        mStatsCard.findViewById(R.id.user_stats_follow_state).setOnClickListener { view ->
            mProgressBar.visibility = View.VISIBLE
            mStatsCard.findViewById(R.id.user_stats_follow_state).isEnabled = false
            if (mUser!!.isFollowing) {
                Friends.unfollow(mUser!!.id, object : JsonCallback<UserModel>() {
                    override fun onError(call: Call, e: Exception, id: Int) {
                        Toast.makeText(this@UserActivity, "取消关注失败", Toast.LENGTH_SHORT).show()
                        mProgressBar.visibility = View.GONE
                        mStatsCard.findViewById(R.id.user_stats_follow_state).isEnabled = true
                    }

                    override fun onResponse(response: UserModel, id: Int) {
                        mUser!!.isFollowing = !mUser!!.isFollowing
                        checkForFollowState()
                        mProgressBar.visibility = View.GONE
                        mStatsCard.findViewById(R.id.user_stats_follow_state).isEnabled = true
                    }
                })
            } else {
                Friends.follow(mUser!!.id, object : JsonCallback<UserModel>() {
                    override fun onError(call: Call, e: Exception, id: Int) {
                        Toast.makeText(this@UserActivity, "关注失败", Toast.LENGTH_SHORT).show()
                        mProgressBar.visibility = View.GONE
                        mStatsCard.findViewById(R.id.user_stats_follow_state).isEnabled = true
                    }

                    override fun onResponse(response: UserModel, id: Int) {
                        mUser!!.isFollowing = !mUser!!.isFollowing
                        checkForFollowState()
                        mProgressBar.visibility = View.GONE
                        mStatsCard.findViewById(R.id.user_stats_follow_state).isEnabled = true
                    }
                })
            }
        }
        Friends.getFollowers(mUser!!.id, 3, 0, object : JsonCallback<UserListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {

            }

            override fun onResponse(response: UserListModel, id: Int) {
                if (isDestroyed) return
                if (response.users!!.isNotEmpty())
                    Glide.with(this@UserActivity).load(response.users!![0].avatarLarge).fitCenter().crossFade().into(mStatsCard.findViewById(R.id.user_stats_follower_icon_1) as CircleImageView)
                if (response.users!!.size > 1)
                    Glide.with(this@UserActivity).load(response.users!![1].avatarLarge).fitCenter().crossFade().into(mStatsCard.findViewById(R.id.user_stats_follower_icon_2) as CircleImageView)
                if (response.users!!.size > 2)
                    Glide.with(this@UserActivity).load(response.users!![2].avatarLarge).fitCenter().crossFade().into(mStatsCard.findViewById(R.id.user_stats_follower_icon_3) as CircleImageView)
            }
        })
        Friends.getFriends(mUser!!.id, 3, 0, object : JsonCallback<UserListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {

            }

            override fun onResponse(response: UserListModel, id: Int) {
                if (isDestroyed) return
                if (response.users!!.isNotEmpty())
                    Glide.with(this@UserActivity).load(response.users!![0].avatarLarge).fitCenter().crossFade().into(mStatsCard.findViewById(R.id.user_stats_following_icon_1) as CircleImageView)
                if (response.users!!.size > 1)
                    Glide.with(this@UserActivity).load(response.users!![1].avatarLarge).fitCenter().crossFade().into(mStatsCard.findViewById(R.id.user_stats_following_icon_2) as CircleImageView)
                if (response.users!!.size > 2)
                    Glide.with(this@UserActivity).load(response.users!![2].avatarLarge).fitCenter().crossFade().into(mStatsCard.findViewById(R.id.user_stats_following_icon_3) as CircleImageView)
            }
        })
    }

    private fun checkForFollowState() {
        if (mUser!!.isFollowing && mUser!!.isFollowMe)
            (mStatsCard.findViewById(R.id.user_stats_follow_state) as Button).text = "互相关注"
        else if (mUser!!.isFollowMe)
            (mStatsCard.findViewById(R.id.user_stats_follow_state) as Button).text = "被关注"
        else if (mUser!!.isFollowing)
            (mStatsCard.findViewById(R.id.user_stats_follow_state) as Button).text = "正在关注"
        else if (mUser!!.id === StaticResource.uid)
            mStatsCard.findViewById(R.id.user_stats_follow_state).visibility = View.INVISIBLE
        else
            (mStatsCard.findViewById(R.id.user_stats_follow_state) as Button).text = "关注"
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_user, menu)
        mMenu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (mUser != null) {
            when (item.itemId) {
                R.id.menu_user_block -> {
                    val blockUserid = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.block_userid_key), null)
                    val blockList: MutableList<String>
                    if (TextUtils.isEmpty(blockUserid))
                        blockList = ArrayList<String>()
                    else
                        blockList = ArrayList(Arrays.asList(*blockUserid!!.split(",".toRegex()).dropLastWhile(String::isEmpty).toTypedArray()))
                    if (!blockList.contains(mUser!!.id.toString())) {
                        blockList.add(mUser!!.id.toString())
                        mMenu!!.findItem(R.id.menu_user_block).title = "已屏蔽"
                    } else {
                        blockList.remove(mUser!!.id.toString())
                        mMenu!!.findItem(R.id.menu_user_block).title = "屏蔽"
                    }
                    PreferenceManager.getDefaultSharedPreferences(this).edit().putString(getString(R.string.block_userid_key), TextUtils.join(",", blockList)).apply()
                }
                R.id.menu_user_blacklist -> Blocks.addBlock(mUser!!.id, object : JsonCallback<UserModel>() {
                    override fun onError(call: Call, e: Exception, id: Int) {

                    }

                    override fun onResponse(response: UserModel, id: Int) {
                        Toast.makeText(this@UserActivity, "丢进黑名单成功", Toast.LENGTH_SHORT).show()
                    }
                })
                R.id.menu_user_directmessage -> {
                    val intent = Intent(this, DirectMessageActivity::class.java)
                    intent.putExtra(getString(R.string.user_item_name), mUser)
                    startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
