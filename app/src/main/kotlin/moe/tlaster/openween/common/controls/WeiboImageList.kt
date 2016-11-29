package moe.tlaster.openween.common.controls

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.piasy.biv.indicator.ProgressIndicator
import com.github.piasy.biv.view.BigImageView
import com.liuguangqiang.swipeback.SwipeBackActivity
import com.liuguangqiang.swipeback.SwipeBackLayout
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.IconicsDrawable

import me.relex.circleindicator.CircleIndicator
import moe.tlaster.openween.R
import moe.tlaster.openween.activity.BaseActivity

/**
 * Created by Asahi on 2016/9/24.
 */

class WeiboImageList : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weibo_image_list)
        val viewPager = findViewById(R.id.weibo_image_viewPager) as ViewPager
        val indicator = findViewById(R.id.weibo_image_indicator) as CircleIndicator
        viewPager.adapter = ImagePagerAdapter(intent.extras.getStringArrayList(INTENTNAME))
        indicator.setViewPager(viewPager)
        viewPager.post { viewPager.setCurrentItem(intent.getIntExtra(POSITIONNAME, 0), false) }
    }


    internal inner class ImagePagerAdapter(private val mData: List<String>) : PagerAdapter() {

        override fun getCount(): Int {
            return mData.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val itemTemplate = LayoutInflater.from(this@WeiboImageList).inflate(R.layout.weibo_image_list_itemtemplate, container, false)
            val imageView = itemTemplate.findViewById(R.id.weibo_image_list_item) as BigImageView
            imageView.showImage(Uri.parse(mData[position]))
            imageView.setProgressIndicator(ImageProgress())
            imageView.setOnClickListener { this@WeiboImageList.finish() }
            container.addView(itemTemplate)
            return itemTemplate
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }

    companion object {
        var INTENTNAME = "images"
        var POSITIONNAME = "position"
    }
    internal inner class ImageProgress : ProgressIndicator {
        private var mProgressBar: ProgressBar? = null

        override fun onFinish() {

        }

        override fun getView(parent: BigImageView?): View {
            mProgressBar = LayoutInflater.from(parent?.context).inflate(R.layout.image_progress, parent, false) as ProgressBar
            return mProgressBar as ProgressBar
        }

        override fun onProgress(progress: Int) {
            if (progress < 0 || progress > 100) return
            mProgressBar?.progress = progress
        }

        override fun onStart() {
        }

    }
}
