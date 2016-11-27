package moe.tlaster.openween.common.helpers

import android.text.TextUtils

import com.zhy.http.okhttp.OkHttpUtils

import java.io.File
import java.util.HashMap

import moe.tlaster.openween.core.api.Entity

/**
 * Created by Tlaster on 2016/9/5.
 */
object HttpHelper {
    fun getAsync(url: String, param: MutableMap<String, String>?, callback: JsonCallback<*>) {
        if (TextUtils.isEmpty(Entity.accessToken)) {
            callback.onError(null, InvalidAccessTokenException(), -1)
            return
        }
        getAsync(url, Entity.accessToken!!, param, callback)
    }

    fun getAsync(url: String, token: String, param: MutableMap<String, String>?, callback: JsonCallback<*>) {
        var param = param
        if (param == null) param = HashMap<String, String>()
        param.put("access_token", token)
        OkHttpUtils.get().url(url).params(param).build().execute(callback)
    }

    fun postAsync(url: String, data: MutableMap<String, String>?, callback: JsonCallback<*>?) {
        var data = data
        if (TextUtils.isEmpty(Entity.accessToken)) {
            callback?.onError(null, InvalidAccessTokenException(), -1)
            return
        }
        if (data == null) data = HashMap<String, String>()
        data.put("access_token", Entity.accessToken!!)
        OkHttpUtils.post().url(url).params(data).build().execute(callback)
    }

    fun uploadFileAsync(url: String, file: File, callback: JsonCallback<*>) {
        if (TextUtils.isEmpty(Entity.accessToken)) {
            callback.onError(null, InvalidAccessTokenException(), -1)
            return
        }
        OkHttpUtils.post().url(url).addFile("pic", "pic.png", file).addParams("access_token", Entity.accessToken).build().execute(callback)
    }

    fun uploadFileWithParamAsync(url: String, file: File, data: MutableMap<String, String>?, callback: JsonCallback<*>) {
        var data = data
        if (TextUtils.isEmpty(Entity.accessToken)) {
            callback.onError(null, InvalidAccessTokenException(), -1)
            return
        }
        if (data == null) data = HashMap<String, String>()
        data.put("access_token", Entity.accessToken!!)
        OkHttpUtils.post().url(url).addFile("pic", "pic.png", file).params(data).build().execute(callback)
    }
}

