package moe.tlaster.openween.common.helpers

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils

import net.grandcentrix.tray.AppPreferences

/**
 * Created by Asahi on 2016/9/26.
 */

object SettingHelper {
    private val DELIMITER = ";"
    val ACCESSTOKEN = "AccessToken"

    fun setSetting(context: Context, name: String, value: String) {
        val preferences = AppPreferences(context)
        preferences.put(name, value)
    }

    fun getSetting(context: Context, name: String): String? {
        val preferences = AppPreferences(context)
        return preferences.getString(name, null)
    }

    fun getBooleanSetting(context: Context, name: String): Boolean {
        val preferences = AppPreferences(context)
        return preferences.getBoolean(name, false)
    }

    fun setListSetting(context: Context, name: String, isReplace: Boolean, vararg value: String) {
        var saveValue = getSetting(context, name)
        if (saveValue == null) {
            saveValue = TextUtils.join(DELIMITER, value)
        } else if (!isReplace) {
            saveValue += ";" + TextUtils.join(DELIMITER, value)
        } else {
            saveValue = TextUtils.join(DELIMITER, value)
        }
        setSetting(context, name, saveValue!!)
    }

    fun getListSetting(context: Context, name: String): Array<String>? {
        val value = getSetting(context, name)
        if (value != null)
            return value.split(DELIMITER.toRegex()).dropLastWhile(String::isEmpty).toTypedArray()
        else
            return null
    }
}
