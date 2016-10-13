package moe.tlaster.openween.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.github.jorgecastilloprz.FABProgressCircle;
import com.github.jorgecastilloprz.listeners.FABProgressListener;
import com.google.gson.JsonParser;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.transitionseverywhere.AutoTransition;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import moe.tlaster.openween.R;
import moe.tlaster.openween.common.StaticResource;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.common.helpers.SettingHelper;
import moe.tlaster.openween.core.api.Entity;
import moe.tlaster.openween.core.api.user.Account;
import moe.tlaster.openween.core.api.user.User;
import moe.tlaster.openween.core.model.LimitStatusModel;
import moe.tlaster.openween.core.model.user.UserModel;
import okhttp3.Call;

import static com.transitionseverywhere.TransitionSet.ORDERING_TOGETHER;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash_progress)
    public FABProgressCircle mFABProgressCircle;
    @BindView(R.id.splash_icon)
    public CircleImageView mCircleImageView;
    private UserModel mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        //ProgressBar progressBar = (ProgressBar) findViewById(R.id.splash_progressbar);
        //progressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        findViewById(R.id.splash_container).post(()->{
            /*TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.splash_icon_container), new TransitionSet()
                    .setOrdering(ORDERING_TOGETHER)
                    //.addTransition(new Slide(Gravity.LEFT))
                    .addTransition(new ChangeBounds())
                    .setDuration(1000).setStartDelay(1000).addListener(new Transition.TransitionListenerAdapter() {
                        @Override
                        public void onTransitionEnd(Transition transition) {
                            TransitionManager.beginDelayedTransition((ViewGroup) SplashActivity.this.findViewById(R.id.splash_container), new AutoTransition().setDuration(500).addListener(new Transition.TransitionListenerAdapter() {
                                @Override
                                public void onTransitionEnd(Transition transition) {
                                    allTransitionEnd();
                                }
                            }));
                            //SplashActivity.this.findViewById(R.id.splash_progress_content).setVisibility(View.VISIBLE);
                        }
                    }));*/
            TransitionManager.beginDelayedTransition((ViewGroup) SplashActivity.this.findViewById(R.id.splash_icon_container), new TransitionSet()
                    .setOrdering(ORDERING_TOGETHER)
                    //.addTransition(new Slide(Gravity.LEFT))
                    .addTransition(new ChangeBounds())
                    .setDuration(1000).setStartDelay(1000).addListener(new Transition.TransitionListenerAdapter() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    allTransitionEnd();
                }
            }));
            findViewById(R.id.splash_title).setVisibility(View.VISIBLE);
        });
    }

    private void allTransitionEnd() {
        mFABProgressCircle.show();
        if (SettingHelper.getListSetting(SplashActivity.this, SettingHelper.ACCESSTOKEN) == null) {
            goLogin();
        } else {
            Entity.setAccessToken(SettingHelper.getListSetting(this, SettingHelper.ACCESSTOKEN)[0]);
            Account.getLimitStatus(new JsonCallback<LimitStatusModel>() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    if (e.getMessage().contains("403")){
                        List<String> list = new LinkedList<>(Arrays.asList(SettingHelper.getListSetting(SplashActivity.this, SettingHelper.ACCESSTOKEN)));
                        list.remove(0);
                        SettingHelper.setListSetting(SplashActivity.this, SettingHelper.ACCESSTOKEN, true, list.toArray(new String[0]));
                        goLogin();
                    }
                }
                @Override
                public void onResponse(LimitStatusModel response, int id) {
                    Account.getUid(new JsonCallback<String>() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            goHome();
                        }
                        @Override
                        public void onResponse(String response, int id) {
                            StaticResource.setUid(new JsonParser().parse(response).getAsJsonObject().get("uid").getAsLong());
                            User.getUser(StaticResource.getUid(), new JsonCallback<UserModel>() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    goHome();
                                }
                                @Override
                                public void onResponse(UserModel response, int id) {
                                    mUser = response;
                                    OkHttpUtils.get().url(response.getAvatarLarge()).build().execute(new BitmapCallback()
                                    {
                                        FrameLayout root;
                                        ImageView imgView;

                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            goHome();
                                        }
                                        @Override
                                        public void onResponse(Bitmap response, int id) {
                                            mFABProgressCircle.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
                                                imgView = (ImageView) v.findViewById(R.id.completeFabIcon);
                                                root = (FrameLayout) v.findViewById(R.id.completeFabRoot);
                                                if ((imgView != null) && (imgView.getScaleType() != ImageView.ScaleType.CENTER_INSIDE)) {
                                                    imgView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                                }
                                            });
                                            mFABProgressCircle.attachListener(()-> new Handler(Looper.getMainLooper()).postDelayed(() -> {
                                                mCircleImageView.setImageBitmap(response);
                                                TransitionManager.beginDelayedTransition(root);
                                                root.setVisibility(View.GONE);
                                                navigate();
                                            }, 1000));
                                            mFABProgressCircle.beginFinalAnimation();
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });
        }
    }

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void goHome() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
        finish();
    }

    private void navigate() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this, mCircleImageView, getString(R.string.user_profile_icon_name));
                i.putExtra("user", mUser);
                startActivity(i, transitionActivityOptions.toBundle());
            } else {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
            finish();
        }, 2000);
    }
}
