package moe.tlaster.openween.common.helpers

/**
 * Created by Tlaster on 2017/2/7.
 */
object Setttings {
    var AccessToken: List<String>
        get() = SettingHelper.get("AccessToken", ArrayList<String>())
        set(value) = SettingHelper.set("AccessToken", value)
}