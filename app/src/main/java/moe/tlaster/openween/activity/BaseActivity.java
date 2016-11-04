package moe.tlaster.openween.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.Explode;
import android.transition.Slide;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import moe.tlaster.openween.core.api.Entity;

//import icepick.Icepick;

/**
 * Created by Asahi on 2016/10/2.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private String mShouldRestart = "ShouldRestart";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Icepick.restoreInstanceState(this, savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getBoolean(mShouldRestart, false)) {
            //It's better to restart the app than restore the state
            Intent intent = new Intent(this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return;
        }
        checkForUpdates();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Icepick.saveInstanceState(this, outState);
        outState.putBoolean(mShouldRestart, true);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterManagers();
    }
    @Override
    public void onResume() {
        super.onResume();
        checkForCrashes();
    }
    @Override
    public void onPause() {
        super.onPause();
        unregisterManagers();
    }
    private void checkForCrashes() {
        CrashManager.register(this);
    }

    private void unregisterManagers() {
        UpdateManager.unregister();
    }

    private void checkForUpdates() {
        UpdateManager.register(this);
    }
}
