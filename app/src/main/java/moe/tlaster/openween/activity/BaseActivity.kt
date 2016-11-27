package moe.tlaster.openween.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.transition.Explode
import android.transition.Slide

import net.hockeyapp.android.CrashManager
import net.hockeyapp.android.UpdateManager
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.reflect.Field
import java.util.Objects

import moe.tlaster.openween.R
import moe.tlaster.openween.common.service.NotificationService
import moe.tlaster.openween.core.api.Entity

import android.app.job.JobInfo.NETWORK_TYPE_ANY

//import icepick.Icepick;

/**
 * Created by Asahi on 2016/10/2.
 */

abstract class BaseActivity : AppCompatActivity() {
    private val mShouldRestart = "ShouldRestart"
    private val INTERVAL = 30 * 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Icepick.restoreInstanceState(this, savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getBoolean(mShouldRestart, false)) {
            //It's better to restart the app than restore the state
            goSplash()
            finish()
            return
        }
        checkForUpdates()
    }

    protected fun goSplash() {
        val intent = Intent(this, SplashActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Icepick.saveInstanceState(this, outState);
        outState.putBoolean(mShouldRestart, true)
    }

    public override fun onDestroy() {
        super.onDestroy()
        unregisterManagers()
    }

    public override fun onResume() {
        super.onResume()
        checkForCrashes()
        stopNotification()
    }

    public override fun onPause() {
        super.onPause()
        unregisterManagers()
        startNotification()
    }

    private fun checkForCrashes() {
        CrashManager.register(this)
    }

    private fun unregisterManagers() {
        UpdateManager.unregister()
    }

    private fun checkForUpdates() {
        UpdateManager.register(this)
    }


    fun startNotification() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, NotificationService::class.java)
        val pendingIntent = PendingIntent.getService(this, 8975, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, 0, interval.toLong(), pendingIntent)
    }

    private val interval: Int
        get() {
            val choices = resources.getStringArray(R.array.notification_interval_list)
            val invertChoice = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.notification_interval_key), getString(R.string.notification_interval_default))
            var interval = 60 * 1000
            if (invertChoice == choices[0]) {
                interval = 60 * 1000
            } else if (invertChoice == choices[1]) {
                interval = 3 * 60 * 1000
            } else if (invertChoice == choices[2]) {
                interval = 5 * 60 * 1000
            } else if (invertChoice == choices[3]) {
                interval = 10 * 60 * 1000
            } else if (invertChoice == choices[4]) {
                interval = 30 * 60 * 1000
            }
            return interval
        }

    fun stopNotification() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, NotificationService::class.java)
        val pendingIntent = PendingIntent.getService(this, 8975, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        alarmManager.cancel(pendingIntent)
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
