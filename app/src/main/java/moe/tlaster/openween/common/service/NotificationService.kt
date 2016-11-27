package moe.tlaster.openween.common.service

import android.app.IntentService
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.preference.PreferenceManager
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import android.text.TextUtils
import android.util.Log

import com.google.gson.Gson

import moe.tlaster.openween.R
import moe.tlaster.openween.activity.MainActivity
import moe.tlaster.openween.common.helpers.DeviceHelper
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.common.helpers.SettingHelper
import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.UnreadModel
import okhttp3.Call

/**
 * Created by Asahi on 2016/11/9.
 */

class NotificationService : IntentService(NotificationService::class.java.name) {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.i(NotificationService::class.java.name, "onHandleIntent")
        val context = applicationContext
        if (!DeviceHelper.checkWifiOnAndConnected(context) || !PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getString(R.string.enable_notification_name), true)) return
        try {
            val accessToken = SettingHelper.getListSetting(context, SettingHelper.ACCESSTOKEN)!![0]
            HttpHelper.getAsync(Constants.REMIND_UNREAD_COUNT, accessToken, null, object : JsonCallback<UnreadModel>() {
                override fun onError(call: Call, e: Exception, id: Int) {

                }

                override fun onResponse(response: UnreadModel, id: Int) {
                    sendNotification(response)
                }
            })
        } catch (e: Exception) {
        }

    }

    private fun sendNotification(unread: UnreadModel) {
        val context = applicationContext
        val prevString = SettingHelper.getSetting(context, getString(R.string.unread_item_name))
        val prevUnread: UnreadModel
        if (prevString == null)
            prevUnread = UnreadModel()
        else
            prevUnread = Gson().fromJson(prevString, UnreadModel::class.java)
        var notifyText = ""
        if (unread.mentionStatus > 0 && unread.mentionStatus != prevUnread.mentionStatus && PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getString(R.string.is_mention_notify_name), true)) {
            notifyText += unread.mentionStatus.toString() + " 条新@ "
        }
        if (unread.cmt > 0 && unread.cmt != prevUnread.cmt && PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getString(R.string.is_comment_notify_name), true)) {
            notifyText += unread.cmt.toString() + " 条新评论 "
        }
        if (unread.mentionCmt > 0 && unread.mentionCmt != prevUnread.mentionCmt && PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getString(R.string.is_mention_notify_name), true)) {
            notifyText += unread.mentionCmt.toString() + " 条新提及的评论 "
        }
        if (unread.follower > 0 && unread.follower != prevUnread.follower && PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getString(R.string.is_follower_notify_name), true)) {
            notifyText += unread.follower.toString() + " 个新粉丝 "
        }
        if (unread.dm > 0 && unread.dm != prevUnread.dm && PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getString(R.string.is_message_notify_name), true)) {
            notifyText += unread.dm.toString() + " 条新私信 "
        }
        if (!TextUtils.isEmpty(notifyText)) {
            val notificationIntent = Intent(context, MainActivity::class.java)
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            val pendingIntent = PendingIntent.getActivity(context, 1, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val mBuilder = NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_notification_icon)
                    .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher))
                    .setContentTitle(getString(R.string.app_name))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setContentText(notifyText)
            notificationManager.notify(1, mBuilder.build())
        }
        SettingHelper.setSetting(context, getString(R.string.unread_item_name), Gson().toJson(unread))
    }
}
