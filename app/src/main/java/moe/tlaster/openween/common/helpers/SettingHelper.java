package moe.tlaster.openween.common.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import net.grandcentrix.tray.AppPreferences;

/**
 * Created by Asahi on 2016/9/26.
 */

public class SettingHelper {
    private final static String DELIMITER = ";";
    public final static String ACCESSTOKEN = "AccessToken";

    public static void setSetting(final Context context, String name, String value) {
        AppPreferences preferences = new AppPreferences(context);
        preferences.put(name, value);
    }
    public static String getSetting(final Context context, String name) {
        AppPreferences preferences = new AppPreferences(context);
        return preferences.getString(name, null);
    }
    public static void setListSetting(final Context context, String name, boolean isReplace, String... value) {
        String saveValue = getSetting(context, name);
        if (saveValue == null){
            saveValue = TextUtils.join(DELIMITER, value);
        } else if (!isReplace) {
            saveValue += ";" + TextUtils.join(DELIMITER, value);
        } else {
            saveValue = TextUtils.join(DELIMITER, value);
        }
        setSetting(context, name, saveValue);
    }
    public static String[] getListSetting(final Context context, String name) {
        String value = getSetting(context, name);
        if (value != null) return value.split(DELIMITER);
        else return null;
    }
}
