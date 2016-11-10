package moe.tlaster.openween.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
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
import java.util.Objects;

import moe.tlaster.openween.R;
import moe.tlaster.openween.common.service.NotificationService;
import moe.tlaster.openween.core.api.Entity;

import static android.app.job.JobInfo.NETWORK_TYPE_ANY;

//import icepick.Icepick;

/**
 * Created by Asahi on 2016/10/2.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private String mShouldRestart = "ShouldRestart";
    private int INTERVAL = 30 * 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Icepick.restoreInstanceState(this, savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getBoolean(mShouldRestart, false)) {
            //It's better to restart the app than restore the state
            goSplash();
            finish();
            return;
        }
        checkForUpdates();
    }

    protected void goSplash() {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
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
        stopNotification();
    }
    @Override
    public void onPause() {
        super.onPause();
        unregisterManagers();
        startNotification();
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


    public void startNotification() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 8975, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, 0, getInterval(), pendingIntent);
    }

    private int getInterval() {
        String[] choices = getResources().getStringArray(R.array.notification_interval_list);
        String invertChoice = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.notification_interval_key), getString(R.string.notification_interval_default));
        int interval = 60 * 1000;
        if (Objects.equals(invertChoice, choices[0])) {
            interval = 60 * 1000;
        } else if (Objects.equals(invertChoice, choices[1])) {
            interval = 3 * 60 * 1000;
        } else if (Objects.equals(invertChoice, choices[2])) {
            interval = 5 * 60 * 1000;
        } else if (Objects.equals(invertChoice, choices[3])) {
            interval = 10 * 60 * 1000;
        } else if (Objects.equals(invertChoice, choices[4])) {
            interval = 30 * 60 * 1000;
        }
        return interval;
    }

    public void stopNotification() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 8975, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
    }

    /*
    protected void startNotification() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(8975, new ComponentName(getPackageName(), NotificationService.class.getName()))
                .setPeriodic(getInterval())
                .setRequiredNetworkType(NETWORK_TYPE_ANY);
        jobScheduler.schedule(builder.build());
    }

    protected void cancelAllJobs() {
        ((JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE)).cancel(8975);
    }
    */
}
