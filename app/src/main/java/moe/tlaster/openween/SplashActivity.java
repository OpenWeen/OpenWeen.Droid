package moe.tlaster.openween;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.transitionseverywhere.AutoTransition;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

import moe.tlaster.openween.common.StaticResource;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.common.helpers.SettingHelper;
import moe.tlaster.openween.core.api.Entity;
import moe.tlaster.openween.core.api.user.Account;
import okhttp3.Call;

import static com.transitionseverywhere.TransitionSet.ORDERING_SEQUENTIAL;
import static com.transitionseverywhere.TransitionSet.ORDERING_TOGETHER;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.splash_progressbar);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        findViewById(R.id.splash_container).post(()->{
            TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.splash_icon_container), new TransitionSet()
                    .setOrdering(ORDERING_TOGETHER)
                    //.addTransition(new Slide(Gravity.LEFT))
                    .addTransition(new ChangeBounds())
                    .setDuration(1000).setStartDelay(1000).addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {

                        }

                        @Override
                        public void onTransitionEnd(Transition transition) {
                            TransitionManager.beginDelayedTransition((ViewGroup) SplashActivity.this.findViewById(R.id.splash_container), new AutoTransition().setDuration(500).addListener(new Transition.TransitionListener() {
                                @Override
                                public void onTransitionStart(Transition transition) {

                                }

                                @Override
                                public void onTransitionEnd(Transition transition) {
                                    allTransitionEnd();
                                }

                                @Override
                                public void onTransitionCancel(Transition transition) {

                                }

                                @Override
                                public void onTransitionPause(Transition transition) {

                                }

                                @Override
                                public void onTransitionResume(Transition transition) {

                                }
                            }));
                            SplashActivity.this.findViewById(R.id.splash_progress_content).setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onTransitionCancel(Transition transition) {

                        }

                        @Override
                        public void onTransitionPause(Transition transition) {

                        }

                        @Override
                        public void onTransitionResume(Transition transition) {

                        }
                    }));
            findViewById(R.id.splash_title).setVisibility(View.VISIBLE);
        });
    }

    private void allTransitionEnd() {
        String[] tokens = SettingHelper.getListSetting(SplashActivity.this, SettingHelper.ACCESSTOKEN);
        if (tokens == null) {
            goLogin();
        } else {
            Entity.setAccessToken(tokens[0]);
            Account.getUid(new JsonCallback<String>() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    goHome();
                }
                @Override
                public void onResponse(String response, int id) {
                    JsonParser parser = new JsonParser();
                    StaticResource.setUid(parser.parse(response).getAsJsonObject().get("uid").getAsLong());
                    navigate();
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
    }

    private void navigate() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
