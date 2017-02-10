package moe.tlaster.openween.common.helpers

import android.support.v4.util.ArrayMap
import android.text.TextUtils
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.HttpException
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpUpload
import com.github.kittinunf.result.Result
import moe.tlaster.openween.common.WeiboException
import java.io.File

/**
 * Created by Tlaster on 2017/1/19.
 */
object HttpHelper {
    inline fun  <reified T: Any> getAsync(url: String, param: Map<String, Any>) : T {
        (param as ArrayMap).put("access_token", Setttings.AccessToken)
        if (T::class.java == String::class.java) {
            return checkForError(url.httpGet(param.toList()).responseJson().third) as T
        } else {
            return ObjectMapper().readValue(checkForError(url.httpGet(param.toList()).responseJson().third))
        }
    }

    //    fun getAsync(url: String, param: Map<String, Any?>?) : String {
//        return checkForError(url.httpGet(param?.toList()).responseJson().third)
//    }
    inline fun  <reified T: Any> postAsync(url: String, param: Map<String, Any>, file: File? = null) : T {
        (param as ArrayMap).put("access_token", Setttings.AccessToken)
        val request = if (file == null) {
            url.httpPost(param.toList())
        } else {
            url.httpUpload(parameters = param.toList()).source { _, _ -> file }
        }
        if (T::class.java == String::class.java) {
            return checkForError(request.responseJson().third) as T
        } else {
            return ObjectMapper().readValue(checkForError(request.responseJson().third))
        }
    }

    fun checkForError(responseJson: Result<Json, FuelError>): String {
        if (responseJson.component2()?.exception != null) throw responseJson.component2()!!
        val value = responseJson.component1()!!
        if (!TextUtils.isEmpty(value.obj().optString("errorCode"))) throw WeiboException(value.obj().optString("error"))
        return value.content
    }
}