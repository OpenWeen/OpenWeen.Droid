package moe.tlaster.openween.activity


import android.annotation.TargetApi
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.CheckBoxPreference
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceActivity
import android.preference.SwitchPreference
import android.support.v7.app.ActionBar
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import android.preference.RingtonePreference
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Toast

import com.afollestad.materialdialogs.MaterialDialog
import com.google.gson.Gson
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.Callback
import com.zhy.http.okhttp.callback.FileCallBack

import net.hockeyapp.android.FeedbackManager

import moe.tlaster.openween.App
import moe.tlaster.openween.R
import moe.tlaster.openween.common.StaticResource
import moe.tlaster.openween.common.helpers.DeviceHelper
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.api.statuses.Emotions
import moe.tlaster.openween.core.model.EmotionModel
import okhttp3.Call
import okhttp3.Response

import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.util.ArrayList

/**
 * A [PreferenceActivity] that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 *
 *
 * See [
   * Android Design: Settings](http://developer.android.com/design/patterns/settings.html) for design guidelines and the [Settings
   * API Guide](http://developer.android.com/guide/topics/ui/settings.html) for more information on developing a Settings UI.
 */
class SettingsActivity : AppCompatPreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setupActionBar();
    }

    /**
     * Set up the [android.app.ActionBar], if the API is available.
     */
    private fun setupActionBar() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * {@inheritDoc}
     */
    override fun onIsMultiPane(): Boolean {
        return isXLargeTablet(this)
    }

    /**
     * {@inheritDoc}
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onBuildHeaders(target: List<PreferenceActivity.Header>) {
        loadHeadersFromResource(R.xml.pref_headers, target)
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    override fun isValidFragment(fragmentName: String): Boolean {
        return PreferenceFragment::class.java.name == fragmentName
                || GeneralPreferenceFragment::class.java.name == fragmentName
                || NotificationPreferenceFragment::class.java.name == fragmentName
                || BlockPreferenceFragment::class.java.name == fragmentName
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class BlockPreferenceFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.pref_block)
            setHasOptionsMenu(false)
            bindPreferenceSummaryToValue(findPreference(getString(R.string.block_text_key)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.block_userid_key)))
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class NotificationPreferenceFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.pref_notification)
            setHasOptionsMenu(false)
            bindPreferenceSummaryToValue(findPreference(getString(R.string.enable_notification_name)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.notification_interval_key)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.is_comment_notify_name)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.is_mention_notify_name)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.is_follower_notify_name)))
            bindPreferenceSummaryToValue(findPreference(getString(R.string.is_message_notify_name)))
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class GeneralPreferenceFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.pref_general)
            setHasOptionsMenu(false)
            bindPreferenceSummaryToValue(findPreference(getString(R.string.enable_animate_key)))
            findPreference(getString(R.string.feedback_key)).setOnPreferenceClickListener { preference ->
                FeedbackManager.showFeedbackActivity(activity)
                true
            }
            findPreference(getString(R.string.download_emotion_key)).setOnPreferenceClickListener { preference ->
                val dialog = arrayOf(MaterialDialog.Builder(activity)
                        .title("正在下载表情")
                        .content(R.string.please_wait)
                        .progress(true, 0)
                        .cancelable(false)
                        .canceledOnTouchOutside(false)
                        .show())
                Emotions.getEmotions(object : JsonCallback<List<EmotionModel>>() {
                    override fun onError(call: Call, e: Exception, id: Int) {
                        dialog[0].dismiss()
                        Toast.makeText(App.context, "下载表情失败", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(responseList: List<EmotionModel>, id: Int) {
                        dialog[0].hide()
                        dialog[0] = MaterialDialog.Builder(activity)
                                .title("正在下载表情")
                                .content(R.string.please_wait)
                                .progress(false, responseList.size, true)
                                .cancelable(false)
                                .canceledOnTouchOutside(false)
                                .show()
                        var result = responseList.toTypedArray()
                        for (i in result.indices) {
                            val item = result[i]
                            if (TextUtils.isEmpty(item.category))
                                item.category = "表情"
                            val fileName = item.value!!.replace("[", "").replace("]", "") + ".jpg"
                            val filePath = activity.getExternalFilesDir(null)!!.path + File.separator + "emotion" + File.separator + item.category + File.separator
                            val finalI = i
                            OkHttpUtils.get().url(item.url).build().execute(object : FileCallBack(filePath, fileName) {
                                override fun onError(call: Call, e: Exception, id: Int) {

                                }

                                override fun onResponse(response: File, id: Int) {
                                    dialog[0].incrementProgress(1)
                                    if (finalI == result.size - 1) {
                                        try {
                                            val file = File(activity.getExternalFilesDir(null)!!.path + File.separator + "emotion" + File.separator + "emotion.json")
                                            if (file.exists()) file.delete()
                                            file.createNewFile()
                                            val outputStreamWriter = OutputStreamWriter(FileOutputStream(file))
                                            outputStreamWriter.write(Gson().toJson(result))
                                            outputStreamWriter.close()
                                        } catch (e: IOException) {
                                            e.printStackTrace()
                                        }

                                        StaticResource.emotions = result.toList()
                                        dialog[0].dismiss()
                                        Toast.makeText(activity, "下载表情完毕", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            })
                            item.url = filePath + fileName
                            result[i] = item
                        }
                    }
                })
                true
            }
            findPreference(getString(R.string.delete_emotion_key)).setOnPreferenceClickListener { preference ->
                val file = File(activity.getExternalFilesDir(null), "emotion")
                if (file.exists()) {
                    val dialog = MaterialDialog.Builder(activity)
                            .title("正在删除表情")
                            .content(R.string.please_wait)
                            .progress(true, 0)
                            .show()
                    DeviceHelper.deleteRecursive(file)
                    dialog.dismiss()
                    Toast.makeText(activity, "删除表情成功", Toast.LENGTH_SHORT).show()
                }
                true
            }
        }
    }

    companion object {
        /**
         * A preference value change listener that updates the preference's summary
         * to reflect its new value.
         */
        private val sBindPreferenceSummaryToValueListener: Preference.OnPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, value ->
            val stringValue = value.toString()

            if (preference is ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                val index = preference.findIndexOfValue(stringValue)

                // Set the summary to reflect the new value.
                preference.setSummary(
                        if (index >= 0)
                            preference.entries[index]
                        else
                            null)

                /*} else if (preference instanceof RingtonePreference) {
            // For ringtone preferences, look up the correct display value
            // using RingtoneManager.
            if (TextUtils.isEmpty(stringValue)) {
                // Empty values correspond to 'silent' (no ringtone).
                preference.setSummary(R.string.pref_ringtone_silent);

            } else {
                Ringtone ringtone = RingtoneManager.getRingtone(
                        preference.getContext(), Uri.parse(stringValue));

                if (ringtone == null) {
                    // Clear the summary if there was a lookup error.
                    preference.setSummary(null);
                } else {
                    // Set the summary to reflect the new ringtone display
                    // name.
                    String name = ringtone.getTitle(preference.getContext());
                    preference.setSummary(name);
                }
            }
        */
            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue)
            }
            true
        }

        /**
         * Helper method to determine if the device has an extra-large screen. For
         * example, 10" tablets are extra-large.
         */
        private fun isXLargeTablet(context: Context): Boolean {
            return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_XLARGE
        }

        /**
         * Binds a preference's summary to its value. More specifically, when the
         * preference's value is changed, its summary (line of text below the
         * preference title) is updated to reflect the value. The summary is also
         * immediately updated upon calling this method. The exact display format is
         * dependent on the type of preference.

         * @see .sBindPreferenceSummaryToValueListener
         */
        private fun bindPreferenceSummaryToValue(preference: Preference) {
            // Set the listener to watch for value changes.
            preference.onPreferenceChangeListener = sBindPreferenceSummaryToValueListener
            if (preference is CheckBoxPreference || preference is SwitchPreference) {
                sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                        PreferenceManager
                                .getDefaultSharedPreferences(preference.context)
                                .getBoolean(preference.key, false))
            } else {
                sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                        PreferenceManager
                                .getDefaultSharedPreferences(preference.context)
                                .getString(preference.key, ""))
            }
        }
    }
}
