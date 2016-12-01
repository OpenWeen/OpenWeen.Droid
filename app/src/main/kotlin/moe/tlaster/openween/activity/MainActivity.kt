package moe.tlaster.openween.activity

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.FloatingActionButton
import android.text.TextUtils
import android.transition.Slide
import android.view.Gravity

import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.holder.ImageHolder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem

import butterknife.ButterKnife
import moe.tlaster.openween.R
import moe.tlaster.openween.common.bindView
import moe.tlaster.openween.common.controls.Pivot
import moe.tlaster.openween.common.entities.PostWeiboType
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.common.helpers.WeiboCardHelper
import moe.tlaster.openween.core.api.friendships.Groups
import moe.tlaster.openween.core.api.remind.Remind
import moe.tlaster.openween.core.model.UnreadModel
import moe.tlaster.openween.core.model.status.GroupListModel
import moe.tlaster.openween.core.model.status.GroupModel
import moe.tlaster.openween.core.model.user.UserModel
import moe.tlaster.openween.fragment.main.*
import okhttp3.Call
import kotlin.concurrent.timer

class MainActivity : BaseActivity() {
    val mPivot: Pivot by bindView(R.id.main_pivot)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!intent.hasExtra("user")) {
            goSplash()
            return
        }
        val user = intent.extras.getParcelable<UserModel>("user")
        //setupWindowAnimations();
        val timeline = Timeline()
        val drawerItemHome: PrimaryDrawerItem =PrimaryDrawerItem().withIdentifier(1).withName("首页").withIcon(GoogleMaterial.Icon.gmd_home).withOnDrawerItemClickListener { view, position, drawerItem ->
                    timeline.toGroup(-1)
                    false
                }
        val drawer = DrawerBuilder()
                .withActivity(this)
                .addDrawerItems(drawerItemHome,
                        DividerDrawerItem(),
                        DividerDrawerItem(),
                        PrimaryDrawerItem().withIdentifier(2).withName("设置").withIcon(GoogleMaterial.Icon.gmd_settings).withOnDrawerItemClickListener { view, position, drawerItem ->
                            startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
                            false
                        },
                        PrimaryDrawerItem().withIdentifier(3).withName("关于").withIcon(GoogleMaterial.Icon.gmd_info).withOnDrawerItemClickListener { view, position, drawerItem ->
                            startActivity(Intent(this@MainActivity, AboutActivity::class.java))
                            false
                        }
                )
                .withCloseOnClick(true)
                .build()
        drawer.setSelection(drawerItemHome, false)
        val pageAdapter = Pivot.FragmentPageAdapter(this, supportFragmentManager)
        pageAdapter.add(timeline)
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(getString(R.string.is_merge_message_key), false))
            pageAdapter.add(Message())
        else {
            pageAdapter.add(Mention())
            pageAdapter.add(Comment())
            pageAdapter.add(CommentMention())
        }
        pageAdapter.add(Attitude())
        pageAdapter.add(DirectMessage())
        mPivot.adapter = pageAdapter
        mPivot.setOffscreenPageLimit(pageAdapter.count)
        mPivot.setProfileImage(user!!.avatarLarge!!)
        mPivot.setProfileImageOnClickListener { view -> WeiboCardHelper.goUserActivity(user.screenName!!, this@MainActivity) }
        val accountHeaderBuilder = AccountHeaderBuilder()
                .withActivity(this@MainActivity)
                .withDrawer(drawer)
                .addProfiles(ProfileDrawerItem().withNameShown(true).withName(user.screenName).withEmail(user.description).withIcon(user.avatarLarge))
                .withOnAccountHeaderListener { view, profile, currentProfile -> false }
        if (!TextUtils.isEmpty(user.coverimage))
            accountHeaderBuilder.withHeaderBackground(ImageHolder(user.coverimage))
        else
            accountHeaderBuilder.withHeaderBackground(ColorDrawable(resources.getColor(R.color.colorPrimary)))
        accountHeaderBuilder.build()
        val fab = findViewById(R.id.main_fab) as FloatingActionButton
        fab.setImageDrawable(IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_add).color(Color.WHITE).sizeDp(24))
        fab.setOnClickListener { view ->
            val i = Intent(this@MainActivity, PostWeiboActivity::class.java)
            i.putExtra(getString(R.string.post_weibo_type_name), PostWeiboType.NewPost)
            startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this@MainActivity).toBundle())
        }
        Groups.getGroups(object : JsonCallback<GroupListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {

            }

            override fun onResponse(response: GroupListModel, id: Int) {
                (response.lists!!.size - 1 downTo 0)
                        .map { response.lists!![it] }
                        .forEach {
                            drawer.addItemAtPosition(PrimaryDrawerItem().withIdentifier(it.id).withName(it.name).withOnDrawerItemClickListener { view, position, drawerItem ->
                                timeline.toGroup(it.id)
                                false
                            }, 3)
                        }
            }
        })
        kotlin.concurrent.timer(period = notifyInterval.toLong()){
            Remind.getUnread(callback = object : JsonCallback<UnreadModel>() {
                override fun onResponse(response: UnreadModel?, id: Int) {
                    if (PreferenceManager.getDefaultSharedPreferences(this@MainActivity).getBoolean(getString(R.string.is_merge_message_key), false)) {
                        var count = 0
                        if (PreferenceManager.getDefaultSharedPreferences(this@MainActivity).getBoolean(getString(R.string.is_mention_notify_name), true)) {
                            count += response?.mentionStatus!!
                            count += response?.mentionCmt!!
                        }
                        if (PreferenceManager.getDefaultSharedPreferences(this@MainActivity).getBoolean(getString(R.string.is_comment_notify_name), true))
                            count += response?.cmt!!
                        if (PreferenceManager.getDefaultSharedPreferences(this@MainActivity).getBoolean(getString(R.string.is_message_notify_name), true))
                            mPivot.setTabBadge(mPivot.tabLayout.tabCount - 1, response?.dm!!)
                        mPivot.setTabBadge(mPivot.tabLayout.tabCount - 2, response?.attitude!!)
                        mPivot.setTabBadge(1, count)
                    } else {
                        if (PreferenceManager.getDefaultSharedPreferences(this@MainActivity).getBoolean(getString(R.string.is_mention_notify_name), true)) {
                            mPivot.setTabBadge(1, response?.mentionStatus!!)
                            mPivot.setTabBadge(3, response?.mentionCmt!!)
                        }
                        if (PreferenceManager.getDefaultSharedPreferences(this@MainActivity).getBoolean(getString(R.string.is_comment_notify_name), true))
                            mPivot.setTabBadge(2, response?.cmt!!)
                        if (PreferenceManager.getDefaultSharedPreferences(this@MainActivity).getBoolean(getString(R.string.is_message_notify_name), true))
                            mPivot.setTabBadge(mPivot.tabLayout.tabCount - 1, response?.dm!!)
                        mPivot.setTabBadge(mPivot.tabLayout.tabCount - 2, response?.attitude!!)
                    }
                }

                override fun onError(call: Call?, e: Exception?, id: Int) {
                }

            })
        }
    }

    private fun setupWindowAnimations() {
        val slide = Slide()
        slide.duration = 500
        slide.slideEdge = Gravity.TOP
        window.enterTransition = slide
    }
}
