package moe.tlaster.openween.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.icons.MaterialDrawerFont;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import moe.tlaster.openween.common.controls.WeiboImageList;
import moe.tlaster.openween.common.helpers.DeviceHelper;
import moe.tlaster.openween.core.model.status.PictureModel;

/**
 * Created by Tlaster on 2016/9/10.
 */
public class WeiboImageAdapter extends NineGridImageViewAdapter<PictureModel> {
    @Override
    protected void onDisplayImage(Context context, ImageView imageView, PictureModel pictureModel) {
        Glide.with(context).load(DeviceHelper.checkWifiOnAndConnected(context) ? pictureModel.toBmiddle() : pictureModel.getThumbnailPic()).placeholder(new IconicsDrawable(context).icon(GoogleMaterial.Icon.gmd_image).color(Color.GRAY).sizeDp(48)).fitCenter().crossFade().listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                imageView.setImageDrawable(new IconicsDrawable(context).icon(GoogleMaterial.Icon.gmd_mood_bad).color(Color.GRAY).sizeDp(48));
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).into(imageView);
    }

    @Override
    protected void onItemImageClick(Context context, int index, List<PictureModel> list) {
        Intent intent = new Intent(context, WeiboImageList.class);
        intent.putStringArrayListExtra(WeiboImageList.INTENTNAME, list.stream().map(PictureModel::toLarge).collect(Collectors.toCollection(ArrayList<String>::new)));
        intent.putExtra(WeiboImageList.POSITIONNAME, index);
        context.startActivity(intent);
    }
}
