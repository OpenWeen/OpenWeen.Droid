package moe.tlaster.openween.common.helpers;

import android.text.TextUtils;

import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import moe.tlaster.openween.core.api.Entity;

/**
 * Created by Tlaster on 2016/9/5.
 */
public class HttpHelper {
    public static void getAsync(String url, Map<String, String> param, JsonCallback callback) throws InvalidAccessTokenException {
        if (TextUtils.isEmpty(Entity.getAccessToken()))
            throw new InvalidAccessTokenException();
        getAsync(url, Entity.getAccessToken(), param, callback);
    }

    public static void getAsync(String url, String token, Map<String, String> param, JsonCallback callback){
        if (param == null) param = new HashMap<>();
        param.put("access_token", token);
        OkHttpUtils.get().url(url).params(param).build().execute(callback);
    }

    public static void postAsync(String url, Map<String, String> data, JsonCallback callback) throws InvalidAccessTokenException {
        if (TextUtils.isEmpty(Entity.getAccessToken()))
            throw new InvalidAccessTokenException();
        if (data == null) data = new HashMap<>();
        data.put("access_token", Entity.getAccessToken());
        OkHttpUtils.post().url(url).params(data).build().execute(callback);
    }

    public static void uploadFileAsync(String url, File file, JsonCallback callback) throws InvalidAccessTokenException {
        if (TextUtils.isEmpty(Entity.getAccessToken()))
            throw new InvalidAccessTokenException();
        OkHttpUtils.post().url(url).addFile("pic","pic.png",file).addParams("access_token", Entity.getAccessToken()).build().execute(callback);
    }

    public static void uploadFileWithParamAsync(String url, File file, Map<String, String> data, JsonCallback callback) throws InvalidAccessTokenException {
        if (TextUtils.isEmpty(Entity.getAccessToken()))
            throw new InvalidAccessTokenException();
        if (data == null) data = new HashMap<>();
        data.put("access_token", Entity.getAccessToken());
        OkHttpUtils.post().url(url).addFile("pic","pic.png",file).params(data).build().execute(callback);
    }
}

