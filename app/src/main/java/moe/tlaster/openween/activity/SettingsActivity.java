package moe.tlaster.openween.activity;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;

import net.hockeyapp.android.FeedbackManager;

import moe.tlaster.openween.App;
import moe.tlaster.openween.R;
import moe.tlaster.openween.common.StaticResource;
import moe.tlaster.openween.common.helpers.DeviceHelper;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.core.api.statuses.Emotions;
import moe.tlaster.openween.core.model.EmotionModel;
import okhttp3.Call;
import okhttp3.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {
    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = (preference, value) -> {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list.
            ListPreference listPreference = (ListPreference) preference;
            int index = listPreference.findIndexOfValue(stringValue);

            // Set the summary to reflect the new value.
            preference.setSummary(
                    index >= 0
                            ? listPreference.getEntries()[index]
                            : null);

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
            preference.setSummary(stringValue);
        }
        return true;
    };

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);
        if (preference instanceof CheckBoxPreference || preference instanceof SwitchPreference) {
            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.getContext())
                            .getBoolean(preference.getKey(), false));
        } else {
            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.getContext())
                            .getString(preference.getKey(), ""));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setupActionBar();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreferenceFragment.class.getName().equals(fragmentName)
                || NotificationPreferenceFragment.class.getName().equals(fragmentName)
                || BlockPreferenceFragment.class.getName().equals(fragmentName);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class BlockPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_block);
            setHasOptionsMenu(false);
            bindPreferenceSummaryToValue(findPreference(getString(R.string.block_text_key)));
            bindPreferenceSummaryToValue(findPreference(getString(R.string.block_userid_key)));
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class NotificationPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_notification);
            setHasOptionsMenu(false);
            bindPreferenceSummaryToValue(findPreference(getString(R.string.enable_notification_name)));
            bindPreferenceSummaryToValue(findPreference(getString(R.string.notification_interval_key)));
            bindPreferenceSummaryToValue(findPreference(getString(R.string.is_comment_notify_name)));
            bindPreferenceSummaryToValue(findPreference(getString(R.string.is_mention_notify_name)));
            bindPreferenceSummaryToValue(findPreference(getString(R.string.is_follower_notify_name)));
            bindPreferenceSummaryToValue(findPreference(getString(R.string.is_message_notify_name)));
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);
            setHasOptionsMenu(false);
            bindPreferenceSummaryToValue(findPreference(getString(R.string.enable_animate_key)));
            findPreference(getString(R.string.feedback_key)).setOnPreferenceClickListener(preference -> {
                FeedbackManager.showFeedbackActivity(getActivity());
                return true;
            });
            findPreference(getString(R.string.download_emotion_key)).setOnPreferenceClickListener(preference -> {
                MaterialDialog[] dialog = {new MaterialDialog.Builder(getActivity())
                        .title("正在下载表情")
                        .content(R.string.please_wait)
                        .progress(true, 0)
                        .cancelable(false)
                        .canceledOnTouchOutside(false)
                        .show()};
                Emotions.getEmotions(new JsonCallback<List<EmotionModel>>() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog[0].dismiss();
                        Toast.makeText(App.getContext(), "下载表情失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<EmotionModel> responseList, int id) {
                        dialog[0].hide();
                        dialog[0] = new MaterialDialog.Builder(getActivity())
                                .title("正在下载表情")
                                .content(R.string.please_wait)
                                .progress(false, responseList.size(), true)
                                .cancelable(false)
                                .canceledOnTouchOutside(false)
                                .show();
                        for (int i = 0; i < responseList.size(); i++) {
                            EmotionModel item = responseList.get(i);
                            if (TextUtils.isEmpty(item.getCategory()))
                                item.setCategory("表情");
                            String fileName = item.getValue().replace("[", "").replace("]", "") + ".jpg";
                            String filePath = getActivity().getExternalFilesDir(null).getPath() + File.separator + "emotion" + File.separator + item.getCategory() + File.separator;
                            int finalI = i;
                            OkHttpUtils.get().url(item.getUrl()).build().execute(new FileCallBack(filePath, fileName) {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(File response, int id) {
                                    dialog[0].incrementProgress(1);
                                    if (finalI == responseList.size() - 1) {
                                        try {
                                            File file = new File(getActivity().getExternalFilesDir(null).getPath() + File.separator + "emotion" + File.separator + "emotion.json");
                                            if (file.exists()) file.delete();
                                            file.createNewFile();
                                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
                                            outputStreamWriter.write(new Gson().toJson(responseList));
                                            outputStreamWriter.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        StaticResource.setEmotions(responseList);
                                        dialog[0].dismiss();
                                        Toast.makeText(getActivity(), "下载表情完毕", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            item.setUrl(filePath + fileName);
                            responseList.set(i, item);
                        }
                    }
                });
                return true;
            });
            findPreference(getString(R.string.delete_emotion_key)).setOnPreferenceClickListener( preference -> {
                File file = new File(getActivity().getExternalFilesDir(null), "emotion");
                if (file.exists()) {
                    MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                            .title("正在删除表情")
                            .content(R.string.please_wait)
                            .progress(true, 0)
                            .show();
                    DeviceHelper.deleteRecursive(file);
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "删除表情成功", Toast.LENGTH_SHORT).show();
                }
                return true;
            });
        }
    }
}
