package moe.tlaster.openween.common.controls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.materialdrawer.icons.MaterialDrawerFont;
import com.mikepenz.materialize.Materialize;
import com.mikepenz.materialize.MaterializeBuilder;
import com.mikepenz.materialize.color.Material;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import moe.tlaster.openween.R;
import moe.tlaster.openween.fragment.WeiboListBase;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Tlaster on 2016/9/9.
 */
public class Pivot extends CoordinatorLayout {

    private FragmentPageAdapter mAdapter;

    public Pivot(Context context) {
        super(context);
        initView(context);
    }

    public Pivot(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public Pivot(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.pivot_template, this);
    }

    public int getCurrentIndex() {
        return ((ViewPager) findViewById(R.id.pivot_container)).getCurrentItem();
    }

    public FragmentPageAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(FragmentPageAdapter adapter) {
        mAdapter = adapter;
        initViewPager(adapter);
    }

    public CircleImageView getProfileImageView(){
        return (CircleImageView) findViewById(R.id.profile_image);
    }

    public TabLayout getTabLayout() {
        return (TabLayout) findViewById(R.id.pivot_tabs);
    }

    private void initViewPager(FragmentPageAdapter adapter) {
        ViewPager viewPager = (ViewPager) findViewById(R.id.pivot_container);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.pivot_tabs);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getHeader(i));
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().setAlpha(1.f);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().setAlpha(0.37f);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                getAdapter().toTop(getCurrentIndex());
            }
        });
        setProfileImage(new IconicsDrawable(getContext())
                .icon(GoogleMaterial.Icon.gmd_person)
                .color(Color.WHITE)
                .sizeDp(24));
    }

    public AppBarLayout getAppBarLayout() {
        return ((AppBarLayout) findViewById(R.id.pivot_appbar));
    }

    public void setOffscreenPageLimit(int limit){
        ((ViewPager) findViewById(R.id.pivot_container)).setOffscreenPageLimit(limit);
    }

    public void setProfileImageOnClickListener(View.OnClickListener listener){
        findViewById(R.id.profile_image).setOnClickListener(listener);
    }

    public void setProfileImage(Drawable drawable){
        ((CircleImageView) findViewById(R.id.profile_image)).setImageDrawable(drawable);
    }

    public void setProfileImage(String url){
        CircleImageView circleImageView = (CircleImageView) findViewById(R.id.profile_image);
        OkHttpUtils.get().url(url).build().execute(new BitmapCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(Bitmap response, int id) {
                circleImageView.setImageBitmap(response);
            }
        });
    }

    public static abstract class PivotItemFragment extends Fragment {
        public abstract IIcon getIcon();
    }

    public static class FragmentPageAdapter extends FragmentStatePagerAdapter {

        private final Context mContext;
        private final List<WeiboListBase> mFragments = new ArrayList<>();
        private final List<IIcon> mIcon = new ArrayList<>();

        public FragmentPageAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;
        }

        public void add(WeiboListBase fragment){
            mFragments.add(fragment);
            mIcon.add(fragment.getIcon());
        }

        public View getHeader(int position){
            View tab = LayoutInflater.from(mContext).inflate(R.layout.tabbar_view, null);
            ImageView img = (ImageView)tab.findViewById(R.id.tabbar_Image);
            Drawable icon = new IconicsDrawable(mContext).icon(mIcon.get(position)).color(Color.WHITE).sizeDp(24);
            img.setImageDrawable(icon);
            tab.setSelected(position == 0);
            tab.setAlpha(tab.isSelected() ? 1.f: 0.37f);
            return tab;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        public void refresh(int currentIndex) {
            mFragments.get(currentIndex).refresh();
        }

        void toTop(int currentIndex) {
            mFragments.get(currentIndex).toTop();
        }
    }
}
