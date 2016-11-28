package moe.tlaster.openween.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.widget.ImageView



import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.jaeger.ninegridimageview.NineGridImageViewAdapter
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.materialdrawer.icons.MaterialDrawerFont

import java.util.ArrayList

import moe.tlaster.openween.common.controls.WeiboImageList
import moe.tlaster.openween.common.helpers.DeviceHelper
import moe.tlaster.openween.core.model.status.PictureModel

/**
 * Created by Tlaster on 2016/9/10.
 */
class WeiboImageAdapter : NineGridImageViewAdapter<PictureModel>() {
    override fun onDisplayImage(context: Context, imageView: ImageView, pictureModel: PictureModel) {
        Glide.with(context).load(if (DeviceHelper.checkWifiOnAndConnected(context)) pictureModel.toBmiddle() else pictureModel.thumbnailPic).placeholder(IconicsDrawable(context).icon(GoogleMaterial.Icon.gmd_image).color(Color.GRAY).sizeDp(48)).fitCenter().crossFade().listener(object : RequestListener<String, GlideDrawable> {
            override fun onException(e: Exception, model: String, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
                imageView.setImageDrawable(IconicsDrawable(context).icon(GoogleMaterial.Icon.gmd_mood_bad).color(Color.GRAY).sizeDp(48))
                return false
            }

            override fun onResourceReady(resource: GlideDrawable, model: String, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                return false
            }
        }).into(imageView)
    }

    override fun onItemImageClick(context: Context?, index: Int, list: List<PictureModel>?) {
        val intent = Intent(context, WeiboImageList::class.java)
        intent.putStringArrayListExtra(WeiboImageList.INTENTNAME, ArrayList(list!!.map { it.toLarge() }))
        intent.putExtra(WeiboImageList.POSITIONNAME, index)
        context!!.startActivity(intent)
    }
}
