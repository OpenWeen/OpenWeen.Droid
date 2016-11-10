package moe.tlaster.openween.common.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import moe.tlaster.openween.R;
import moe.tlaster.openween.activity.MainActivity;
import moe.tlaster.openween.common.helpers.DeviceHelper;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.common.helpers.SettingHelper;
import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.UnreadModel;
import okhttp3.Call;

/**
 * Created by Asahi on 2016/11/9.
 */

public class NotificationService extends IntentService  {
    public NotificationService() {
        super(NotificationService.class.getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(NotificationService.class.getName(), "onHandleIntent");
        Context context = getApplicationContext();
        if (!DeviceHelper.checkWifiOnAndConnected(context) || !PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getString(R.string.enable_notification_name), true)) return;
        try {
            String accessToken = SettingHelper.getListSetting(context, SettingHelper.ACCESSTOKEN)[0];
            HttpHelper.getAsync(Constants.REMIND_UNREAD_COUNT, accessToken, null, new JsonCallback<UnreadModel>() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(UnreadModel response, int id) {
                    sendNotification(response);
                }
            });
        } catch (Exception e) {
        }
    }

    private void sendNotification(UnreadModel unread) {
        Context context = getApplicationContext();
        String prevString = SettingHelper.getSetting(context, getString(R.string.unread_item_name));
        UnreadModel prevUnread;
        if (prevString == null)
            prevUnread= new UnreadModel();
        else
            prevUnread = new Gson().fromJson(prevString, UnreadModel.class);
        String notifyText = "";
        if (unread.getMentionStatus() > 0 && unread.getMentionStatus() != prevUnread.getMentionStatus() && PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getString(R.string.is_mention_notify_name), true)) {
            notifyText += String.valueOf(unread.getMentionStatus()) + " 条新@ ";
        }
        if (unread.getCmt() > 0 && unread.getCmt() != prevUnread.getCmt() && PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getString(R.string.is_comment_notify_name), true)) {
            notifyText += String.valueOf(unread.getCmt()) + " 条新评论 ";
        }
        if (unread.getMentionCmt() > 0 && unread.getMentionCmt() != prevUnread.getMentionCmt() && PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getString(R.string.is_mention_notify_name), true)) {
            notifyText += String.valueOf(unread.getMentionCmt()) + " 条新提及的评论 ";
        }
        if (unread.getFollower() > 0 && unread.getFollower() != prevUnread.getFollower() && PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getString(R.string.is_follower_notify_name), true)) {
            notifyText += String.valueOf(unread.getFollower()) + " 个新粉丝 ";
        }
        if (unread.getDm() > 0 && unread.getDm() != prevUnread.getDm() && PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getString(R.string.is_message_notify_name), true)) {
            notifyText += String.valueOf(unread.getDm()) + " 条新私信 ";
        }
        if (!TextUtils.isEmpty(notifyText)) {
            Intent notificationIntent = new Intent(context, MainActivity.class);
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.ic_notification_icon)
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                            .setContentTitle(getString(R.string.app_name))
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)
                            .setContentText(notifyText);
            notificationManager.notify(1, mBuilder.build());
        }
        SettingHelper.setSetting(context, getString(R.string.unread_item_name), new Gson().toJson(unread));
    }
}
