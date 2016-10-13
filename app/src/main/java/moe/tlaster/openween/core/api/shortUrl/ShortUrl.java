package moe.tlaster.openween.core.api.shortUrl;

import java.util.HashMap;
import java.util.Map;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.url.UrlInfoListModel;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/7.
 */
public class ShortUrl {
    public static void shorten(String url, JsonCallback<String> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("url_long", url);
        HttpHelper.getAsync(Constants.SHORT_URL_SHORTEN, param, callback);
    }
    public static void expand(String url, JsonCallback<String> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("url_short", url);
        HttpHelper.getAsync(Constants.SHORT_URL_EXPAND, param, callback);
    }
    public static void info(JsonCallback<UrlInfoListModel> callback, String... urls) {
        Map<String, String> param = new HashMap<>();
        for (String url : urls) {
            param.put("url_short", url);
        }
        HttpHelper.getAsync(Constants.SHORT_URL_INFO, param, callback);
    }
}
