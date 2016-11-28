package moe.tlaster.openween.common.helpers

import com.google.gson.Gson
import com.zhy.http.okhttp.callback.Callback

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

import okhttp3.Response

/**
 * Created by Tlaster on 2016/9/5.
 */
abstract class JsonCallback<T> : Callback<T>() {

    @Throws(Exception::class)
    override fun parseNetworkResponse(response: Response, id: Int): T {
        val string = response.body().string()
        val entityClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        if (entityClass === String::class.java) {
            return string as T
        }
        return Gson().fromJson<T>(string, entityClass)
    }
}
