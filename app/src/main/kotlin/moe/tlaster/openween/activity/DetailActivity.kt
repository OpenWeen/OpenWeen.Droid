package moe.tlaster.openween.activity

import android.graphics.Color
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast

import com.transitionseverywhere.Fade
import com.transitionseverywhere.Slide
import com.transitionseverywhere.TransitionManager

import moe.tlaster.openween.R
import moe.tlaster.openween.common.bindView
import moe.tlaster.openween.common.controls.Pivot
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.common.helpers.WeiboCardHelper
import moe.tlaster.openween.core.api.statuses.Query
import moe.tlaster.openween.core.model.status.MessageModel
import moe.tlaster.openween.fragment.detail.Comment
import moe.tlaster.openween.fragment.detail.Repost
import okhttp3.Call

class DetailActivity : BaseActivity() {
    val mAppBarLayout: AppBarLayout by bindView(R.id.detail_appbar)
    val mTabLayout: TabLayout by bindView(R.id.detail_tab)
    val mViewPager: ViewPager by bindView(R.id.detail_viewPager)
    val mWeibo: View by bindView(R.id.detail_weibo)
    val mProgressBar: ProgressBar by bindView(R.id.detail_progressBar)
    val mToolbar: Toolbar by bindView(R.id.detail_toolbar)
    private var mMessageModel: MessageModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(mToolbar)
        mProgressBar.indeterminateDrawable.setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN)
        mMessageModel = intent.extras.getParcelable<MessageModel>(getString(R.string.detail_message_model_name))
        val pageAdapter = Pivot.FragmentPageAdapter(this, supportFragmentManager)
        pageAdapter.add(Repost.create(mMessageModel!!.id))
        pageAdapter.add(Comment.create(mMessageModel!!.id))
        mViewPager.adapter = pageAdapter
        mTabLayout.setupWithViewPager(mViewPager)
        for (i in 0..mTabLayout.tabCount - 1) {
            val tab = mTabLayout.getTabAt(i)
            tab!!.customView = pageAdapter.getHeader(i)
        }

        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.customView!!.alpha = 1f
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.customView!!.alpha = 0.37f
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
        if (mMessageModel!!.text.contains("\u5168\u6587\uff1a http://m.weibo.cn/") || mMessageModel!!.text.contains("http://m.weibo.cn/client/version")) {
            Query.getStatus(mMessageModel!!.id, true, object : JsonCallback<MessageModel>() {
                override fun onError(call: Call, e: Exception, id: Int) {
                    mProgressBar.visibility = View.GONE
                    Toast.makeText(this@DetailActivity, "获取长微博失败", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(response: MessageModel, id: Int) {
                    mMessageModel = response
                    response.text = response.longText!!.content!!
                    WeiboCardHelper.setData(mWeibo, response, this@DetailActivity, true, Color.WHITE)
                    TransitionManager.beginDelayedTransition(mProgressBar.parent as LinearLayout)
                    mProgressBar.visibility = View.GONE
                    mWeibo.visibility = View.VISIBLE
                }
            })
        } else {
            WeiboCardHelper.setData(mWeibo, mMessageModel!!, this, true, Color.WHITE)
            mProgressBar.visibility = View.GONE
            mWeibo.visibility = View.VISIBLE
        }
    }
}
