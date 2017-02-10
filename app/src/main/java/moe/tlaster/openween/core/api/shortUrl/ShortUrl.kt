package moe.tlaster.openween.core.api.shortUrl

import com.github.kittinunf.fuel.android.core.Json
import android.support.v4.util.ArrayMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.url.UrlInfoListModel
import moe.tlaster.openween.common.helpers.HttpHelper
import org.json.JSONObject

/**
 * Created by Tlaster on 2016/9/7.
 */
object ShortUrl {
    fun shorten(url: String) : String {
        val param = ArrayMap<String, String>()
        param.put("url_long", url)
        return Json(HttpHelper.getAsync(Constants.SHORT_URL_SHORTEN, param)).obj().getJSONArray("urls").optJSONObject(0).getString("url_short")
    }

    fun expand(url: String) : String {
        val param = ArrayMap<String, String>()
        param.put("url_short", url)
        return Json(HttpHelper.getAsync(Constants.SHORT_URL_EXPAND, param)).obj().getJSONArray("urls").optJSONObject(0).getString("url_long")
    }

    fun info(vararg urls: String) : UrlInfoListModel {
        val param = ArrayMap<String, String>()
        for (url in urls) {
            param.put("url_short", url)
        }
        return HttpHelper.getAsync(Constants.SHORT_URL_INFO, param)
    }
}
