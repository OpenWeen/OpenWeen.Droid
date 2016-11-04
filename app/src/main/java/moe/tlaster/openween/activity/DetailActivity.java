package moe.tlaster.openween.activity;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.tlaster.openween.R;
import moe.tlaster.openween.common.controls.Pivot;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.common.helpers.WeiboCardHelper;
import moe.tlaster.openween.core.api.statuses.Query;
import moe.tlaster.openween.core.model.status.MessageModel;
import moe.tlaster.openween.fragment.detail.Comment;
import moe.tlaster.openween.fragment.detail.Repost;
import okhttp3.Call;

public class DetailActivity extends BaseActivity {
    @BindView(R.id.detail_appbar)
    public AppBarLayout mAppBarLayout;
    @BindView(R.id.detail_tab)
    public TabLayout mTabLayout;
    @BindView(R.id.detail_viewPager)
    public ViewPager mViewPager;
    @BindView(R.id.detail_weibo)
    public View mWeibo;
    @BindView(R.id.detail_progressBar)
    public ProgressBar mProgressBar;
    @BindView(R.id.detail_toolbar)
    public Toolbar mToolbar;
    private MessageModel mMessageModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mProgressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);
        mMessageModel = getIntent().getExtras().getParcelable(getString(R.string.detail_message_model_name));
        Pivot.FragmentPageAdapter pageAdapter = new Pivot.FragmentPageAdapter(this, getSupportFragmentManager());
        pageAdapter.add(Repost.create(mMessageModel.getID()));
        pageAdapter.add(Comment.create(mMessageModel.getID()));
        mViewPager.setAdapter(pageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(pageAdapter.getHeader(i));
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().setAlpha(1.f);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().setAlpha(0.37f);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
        int index = mMessageModel.getText().toString().indexOf("\u5168\u6587\uff1a http://m.weibo.cn/");
        if (index != -1) {
            Query.getStatus(mMessageModel.getID(), true, new JsonCallback<MessageModel>() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    mProgressBar.setVisibility(View.GONE);
                    Toast.makeText(DetailActivity.this, "获取长微博失败", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(MessageModel response, int id) {
                    mMessageModel = response;
                    response.setText(response.getLongText().getContent());
                    WeiboCardHelper.setData(mWeibo, response, DetailActivity.this, true, Color.WHITE);
                    TransitionManager.beginDelayedTransition((LinearLayout) mProgressBar.getParent());
                    mProgressBar.setVisibility(View.GONE);
                    mWeibo.setVisibility(View.VISIBLE);
                }
            });
        } else {
            WeiboCardHelper.setData(mWeibo, mMessageModel, this, true, Color.WHITE);
            mProgressBar.setVisibility(View.GONE);
            mWeibo.setVisibility(View.VISIBLE);
        }
    }
}
