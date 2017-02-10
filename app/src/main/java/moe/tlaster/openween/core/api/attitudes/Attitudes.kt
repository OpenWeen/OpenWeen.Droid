package moe.tlaster.openween.core.api.attitudes

import android.support.v4.util.ArrayMap
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.android.core.Json

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.core.model.attitude.AttitudeListModel
import moe.tlaster.openween.core.model.attitude.AttitudeModel
import org.json.JSONObject

/**
 * Created by Tlaster on 2016/9/2.
 */
object Attitudes {
    fun like(id: Long) : Boolean {
        val param = ArrayMap<String, String>()
        param.put("attitude", "smile")
        param.put("id", id.toString())
        return Json(HttpHelper.postAsync(Constants.ATTITUDE_CREATE, param)).obj().optString("attitude") == "smile"
    }

    fun unLike(id: Long) : Boolean {
        val param = ArrayMap<String, String>()
        param.put("id", id.toString())
        return Json(HttpHelper.postAsync(Constants.ATTITUDE_DESTROY, param)).obj().optBoolean("result")
    }
    fun likeToMe(with_common_attitude: Boolean = true, with_comment: Boolean = true, since_id: Long = 0, max_id: Long = 0, count: Int = 20, page: Int = 1) : AttitudeListModel {
        val param = ArrayMap<String, String>()
        param.put("with_common_attitude", if (with_common_attitude) "1" else "0")
        param.put("with_comment", if (with_comment) "1" else "0")
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("source", "211160679")
        param.put("from", "1055095010")
        return HttpHelper.getAsync("https://api.weibo.cn/2/like/to_me", param)
    }
}
