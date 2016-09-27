package moe.tlaster.openween.common.controls;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.liuguangqiang.swipeback.SwipeBackActivity;
import com.liuguangqiang.swipeback.SwipeBackLayout;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import moe.tlaster.openween.R;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Asahi on 2016/9/24.
 */

public class WeiboImageList extends AppCompatActivity {
    public static String INTENTNAME = "images";
    public static String POSITIONNAME = "position";
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weibo_image_list);
        ViewPager viewPager = (ViewPager) findViewById(R.id.weibo_image_viewPager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.weibo_image_indicator);
        mSwipeBackLayout = (SwipeBackLayout) findViewById(R.id.weibo_image_swipeback);
        mSwipeBackLayout.setEnableFlingBack(false);
        mSwipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.BOTTOM);
        viewPager.setAdapter(new ImagePagerAdapter(getIntent().getExtras().getStringArrayList(INTENTNAME)));
        indicator.setViewPager(viewPager);
        viewPager.post(()->{
           viewPager.setCurrentItem(getIntent().getIntExtra(POSITIONNAME, 0), false);
        });
    }



    public class ImagePagerAdapter extends PagerAdapter {
        private List<String> mData;

        ImagePagerAdapter(List<String> data) {
            mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemTemplate = LayoutInflater.from(WeiboImageList.this).inflate(R.layout.weibo_image_list_itemtemplate, container, false);
            PhotoView imageView = (PhotoView) itemTemplate.findViewById(R.id.weibo_image_list_item);
            imageView.setOnClickListener(v -> WeiboImageList.this.finish());
            Glide.with(WeiboImageList.this).load(mData.get(position)).placeholder(new IconicsDrawable(WeiboImageList.this).icon(GoogleMaterial.Icon.gmd_image).color(Color.GRAY).sizeDp(48)).fitCenter().crossFade().listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    imageView.setImageDrawable(new IconicsDrawable(WeiboImageList.this).icon(GoogleMaterial.Icon.gmd_mood_bad).color(Color.GRAY).sizeDp(48));
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);//.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    attacher.setOnScaleChangeListener((scaleFactor, focusX, focusY) -> {
                        if (attacher.getScale() > 1.f)
                            WeiboImageList.this.mSwipeBackLayout.setEnablePullToBack(false);
                        else
                            WeiboImageList.this.mSwipeBackLayout.setEnablePullToBack(true);
                    });
                    imageView.post(() -> {
                        DisplayMetrics displayMetrics = WeiboImageList.this.getResources().getDisplayMetrics();
                        attacher.setScale(displayMetrics.widthPixels / ((float) resource.getIntrinsicWidth()));
                    });
                    return false;
                }

            }).into(imageView);
            container.addView(itemTemplate);
            return itemTemplate;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView((View) object);
        }
    }
}
