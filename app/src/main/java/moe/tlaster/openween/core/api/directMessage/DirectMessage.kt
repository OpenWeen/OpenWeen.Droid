package moe.tlaster.openween.core.api.directMessage

import android.support.v4.util.ArrayMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.directmessage.DirectMessageListModel
import moe.tlaster.openween.core.model.directmessage.DirectMessageModel
import moe.tlaster.openween.core.model.directmessage.DirectMessageUserListModel
import moe.tlaster.openween.common.helpers.HttpHelper

/**
 * Created by Tlaster on 2016/9/6.
 */
object DirectMessage {
    fun getUserList(count: Int = 20, cursor: Int = 0) : DirectMessageUserListModel {
        val param = ArrayMap<String, String>()
        param.put("count", count.toString())
        param.put("cursor", cursor.toString())
        return HttpHelper.getAsync(Constants.DIRECT_MESSAGES_USER_LIST, param)
    }

    fun getConversation(uid: String, since_id: Long = 0, max_id: Long = 0, count: Int = 20, page: Int = 1) : DirectMessageListModel {
        val param = ArrayMap<String, String>()
        param.put("uid", uid)
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        return HttpHelper.getAsync(Constants.DIRECT_MESSAGES_CONVERSATION, param)
    }

    fun getDirectMessages(since_id: Long = 0, max_id: Long = 0, count: Int = 20, page: Int = 1) : DirectMessageListModel {
        val param = ArrayMap<String, String>()
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        return HttpHelper.getAsync(Constants.DIRECT_MESSAGES, param)
    }

    fun send(uid: Long, text: String, id: Long = -1) : DirectMessageModel {
        val param = ArrayMap<String, String>()
        param.put("uid", uid.toString())
        param.put("text", text)
        if (id > 0) param.put("id", id.toString())
        return HttpHelper.postAsync(Constants.DIRECT_MESSAGES_SEND, param)
    }
}
