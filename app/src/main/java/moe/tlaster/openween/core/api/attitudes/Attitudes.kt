package moe.tlaster.openween.core.api.attitudes

import java.util.HashMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback

/**
 * Created by Tlaster on 2016/9/2.
 */
object Attitudes {
    fun like(id: Long, callback: JsonCallback<String>) {
        val param = HashMap<String, String>()
        param.put("attitude", "smile")
        param.put("id", id.toString())
        HttpHelper.postAsync(Constants.ATTITUDE_CREATE, param, callback)
    }

    fun unLike(id: Long, callback: JsonCallback<String>) {
        val param = HashMap<String, String>()
        param.put("id", id.toString())
        HttpHelper.postAsync(Constants.ATTITUDE_DESTROY, param, callback)
    }
    fun likeToMe(callback: JsonCallback<String>, with_common_attitude: Boolean = true, with_comment: Boolean = true, since_id: Long = 0, max_id: Long = 0, count: Int = 20, page: Int = 1) {
        val param = HashMap<String, String>()
        param.put("with_common_attitude", if (with_common_attitude) "1" else "0")
        param.put("with_comment", if (with_comment) "1" else "0")
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("source", "211160679")
        param.put("from", "1055095010")

    }
}
