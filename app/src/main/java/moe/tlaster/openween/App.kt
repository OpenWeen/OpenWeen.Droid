package moe.tlaster.openween

import android.app.Application
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader
import com.mikepenz.materialdrawer.util.DrawerImageLoader
import com.mikepenz.materialdrawer.util.DrawerUIUtils

import moe.tlaster.openween.common.service.NotificationService

import android.app.job.JobInfo.NETWORK_TYPE_ANY

/**
 * Created by Tlaster on 2016/9/10.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        DrawerImageLoader.init(object : AbstractDrawerImageLoader() {
            override fun set(imageView: ImageView?, uri: Uri?, placeholder: Drawable?) {
                Glide.with(imageView!!.context).load(uri).placeholder(placeholder).centerCrop().into(imageView)
            }

            override fun cancel(imageView: ImageView?) {
                Glide.clear(imageView!!)
            }

            override fun placeholder(ctx: Context, tag: String?): Drawable {
                if (DrawerImageLoader.Tags.PROFILE.name == tag) {
                    return DrawerUIUtils.getPlaceHolder(ctx)
                } else if (DrawerImageLoader.Tags.ACCOUNT_HEADER.name == tag) {
                    return IconicsDrawable(ctx).iconText(" ").backgroundColorRes(com.mikepenz.materialdrawer.R.color.primary).sizeDp(56)
                } else if ("customUrlItem" == tag) {
                    return IconicsDrawable(ctx).iconText(" ").backgroundColorRes(R.color.md_red_500).sizeDp(56)
                }
                return super.placeholder(ctx, tag)
            }
        })
    }

    companion object {

        var context: Context? = null
            private set
    }
}
