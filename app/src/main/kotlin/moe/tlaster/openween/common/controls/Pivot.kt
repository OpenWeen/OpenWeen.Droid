package moe.tlaster.openween.common.controls

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toolbar

import com.bumptech.glide.Glide
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.IIcon
import com.mikepenz.materialdrawer.icons.MaterialDrawerFont
import com.mikepenz.materialize.Materialize
import com.mikepenz.materialize.MaterializeBuilder
import com.mikepenz.materialize.color.Material
import com.transitionseverywhere.TransitionManager
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.BitmapCallback

import java.util.ArrayList

import de.hdodenhof.circleimageview.CircleImageView
import moe.tlaster.openween.R
import moe.tlaster.openween.fragment.WeiboListBase
import okhttp3.Call
import okhttp3.Request

/**
 * Created by Tlaster on 2016/9/9.
 */
class Pivot : CoordinatorLayout {

    var adapter: FragmentPageAdapter? = null
        set(adapter) {
            field = adapter
            initViewPager(adapter!!)
        }

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.pivot_template, this)
    }

    val currentIndex: Int
        get() = (findViewById(R.id.pivot_container) as ViewPager).currentItem

    val profileImageView: CircleImageView
        get() = findViewById(R.id.profile_image) as CircleImageView

    val tabLayout: TabLayout
        get() = findViewById(R.id.pivot_tabs) as TabLayout

    private fun initViewPager(adapter: FragmentPageAdapter) {
        val viewPager = findViewById(R.id.pivot_container) as ViewPager
        for (i in 0..adapter.Fragments.lastIndex) {
            adapter.Fragments[i].OnRefresh = Runnable { setTabBadge(i, 0) }
        }
        viewPager.adapter = adapter
        val tabLayout = findViewById(R.id.pivot_tabs) as TabLayout
        tabLayout.setupWithViewPager(viewPager)
        for (i in 0..tabLayout.tabCount - 1) {
            val tab = tabLayout.getTabAt(i)
            tab!!.customView = adapter.getHeader(i)
        }
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.customView!!.alpha = 1f
                if ((tab.customView?.findViewById(R.id.tabbar_text) as TextView).text.isNotEmpty() && (tab.customView?.findViewById(R.id.tabbar_text) as TextView).text.toString().toInt() > 0) {
                    adapter.refresh(currentIndex)
                    setTabBadge(currentIndex, 0)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.customView!!.alpha = 0.37f
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                adapter.toTop(currentIndex)
            }
        })
        setProfileImage(IconicsDrawable(context)
                .icon(GoogleMaterial.Icon.gmd_person)
                .color(Color.WHITE)
                .sizeDp(24))
    }

    val appBarLayout: AppBarLayout
        get() = findViewById(R.id.pivot_appbar) as AppBarLayout

    fun setTabBadge(index: Int, badge: Int) {
        val tabLayout = findViewById(R.id.pivot_tabs) as TabLayout
        val tab = tabLayout.getTabAt(index)
        if (tab?.customView != null) {
            TransitionManager.beginDelayedTransition(tab?.customView!! as ViewGroup)
            if (badge > 99)
                (tab?.customView?.findViewById(R.id.tabbar_text) as TextView).text = 99.toString()
            else
                (tab?.customView?.findViewById(R.id.tabbar_text) as TextView).text = badge.toString()
            if (badge > 0)
                tab?.customView?.findViewById(R.id.tabbar_text)?.visibility = View.VISIBLE
            else
                tab?.customView?.findViewById(R.id.tabbar_text)?.visibility = View.GONE
        }
    }

    fun setOffscreenPageLimit(limit: Int) {
        (findViewById(R.id.pivot_container) as ViewPager).offscreenPageLimit = limit
    }

    fun setProfileImageOnClickListener(listener: (Any) -> Unit) {
        findViewById(R.id.profile_image).setOnClickListener(listener)
    }

    fun setProfileImage(drawable: Drawable) {
        (findViewById(R.id.profile_image) as CircleImageView).setImageDrawable(drawable)
    }

    fun setProfileImage(url: String) {
        val circleImageView = findViewById(R.id.profile_image) as CircleImageView
        OkHttpUtils.get().url(url).build().execute(object : BitmapCallback() {
            override fun onError(call: Call, e: Exception, id: Int) {
            }

            override fun onResponse(response: Bitmap, id: Int) {
                circleImageView.setImageBitmap(response)
            }
        })
    }

    abstract class PivotItemFragment : Fragment() {
        abstract val icon: IIcon
    }

    class FragmentPageAdapter(private val mContext: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        private val mFragments = ArrayList<WeiboListBase<*>>()
        private val mIcon = ArrayList<IIcon>()
        val Fragments: ArrayList<WeiboListBase<*>> get() = mFragments
        fun add(fragment: WeiboListBase<*>) {
            mFragments.add(fragment)
            mIcon.add(fragment.icon)
        }

        fun getHeader(position: Int): View {
            val tab = LayoutInflater.from(mContext).inflate(R.layout.tabbar_view, null)
            val img = tab.findViewById(R.id.tabbar_Image) as ImageView
            val icon = IconicsDrawable(mContext).icon(mIcon[position]).color(Color.WHITE).sizeDp(24)
            img.setImageDrawable(icon)
            tab.isSelected = position == 0
            tab.alpha = if (tab.isSelected) 1f else 0.37f
            return tab
        }

        override fun getItem(position: Int): Fragment {
            return mFragments[position]
        }

        override fun getCount(): Int {
            return mFragments.size
        }

        fun refresh(currentIndex: Int) {
            mFragments[currentIndex].refresh()
        }

        internal fun toTop(currentIndex: Int) {
            mFragments[currentIndex].toTop()
        }
    }
}
