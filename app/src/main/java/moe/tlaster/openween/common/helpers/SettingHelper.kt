package moe.tlaster.openween.common.helpers

import android.preference.PreferenceManager
import moe.tlaster.openween.App

/**
 * Created by Tlaster on 2017/2/7.
 */
object SettingHelper {
    inline fun <reified T: Any> get(key: String, defaultValue: T) : T {
        when (defaultValue) {
            is Boolean -> return PreferenceManager.getDefaultSharedPreferences(App.AppContext).getBoolean(key, defaultValue) as T
            is Int -> return PreferenceManager.getDefaultSharedPreferences(App.AppContext).getInt(key, defaultValue) as T
            is List<*> -> return PreferenceManager.getDefaultSharedPreferences(App.AppContext).getStringSet(key, defaultValue.map(Any?::toString).toSet()) as T
            else -> return PreferenceManager.getDefaultSharedPreferences(App.AppContext).getString(key, defaultValue.toString()) as T
        }
    }
    inline fun <reified T: Any> set(key: String, value: T) {
        when (value) {
            is String -> PreferenceManager.getDefaultSharedPreferences(App.AppContext).edit().putString(key, value).apply()
            is Int -> PreferenceManager.getDefaultSharedPreferences(App.AppContext).edit().putInt(key, value).apply()
            is List<*> -> PreferenceManager.getDefaultSharedPreferences(App.AppContext).edit().putStringSet(key, value.map(Any?::toString).toSet()).apply()
            is Boolean -> PreferenceManager.getDefaultSharedPreferences(App.AppContext).edit().putBoolean(key, value).apply()
        }

    }
}