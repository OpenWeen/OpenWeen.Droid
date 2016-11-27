package moe.tlaster.openween.core.api.shortUrl

import java.util.HashMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.url.UrlInfoListModel
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.JsonCallback

/**
 * Created by Tlaster on 2016/9/7.
 */
object ShortUrl {
    fun shorten(url: String, callback: JsonCallback<String>) {
        val param = HashMap<String, String>()
        param.put("url_long", url)
        HttpHelper.getAsync(Constants.SHORT_URL_SHORTEN, param, callback)
    }

    fun expand(url: String, callback: JsonCallback<String>) {
        val param = HashMap<String, String>()
        param.put("url_short", url)
        HttpHelper.getAsync(Constants.SHORT_URL_EXPAND, param, callback)
    }

    fun info(callback: JsonCallback<UrlInfoListModel>, vararg urls: String) {
        val param = HashMap<String, String>()
        for (url in urls) {
            param.put("url_short", url)
        }
        HttpHelper.getAsync(Constants.SHORT_URL_INFO, param, callback)
    }
}
