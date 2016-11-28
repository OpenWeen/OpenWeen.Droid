package moe.tlaster.openween.core.api.directMessage

import java.util.HashMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.directmessage.DirectMessageListModel
import moe.tlaster.openween.core.model.directmessage.DirectMessageModel
import moe.tlaster.openween.core.model.directmessage.DirectMessageUserListModel
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback

/**
 * Created by Tlaster on 2016/9/6.
 */
object DirectMessage {
    fun getUserList(count: Int = 20, cursor: Int = 0, callback: JsonCallback<DirectMessageUserListModel>) {
        val param = HashMap<String, String>()
        param.put("count", count.toString())
        param.put("cursor", cursor.toString())
        HttpHelper.getAsync(Constants.DIRECT_MESSAGES_USER_LIST, param, callback)
    }

    fun getConversation(uid: String, since_id: Long = 0, max_id: Long = 0, count: Int = 20, page: Int = 1, callback: JsonCallback<DirectMessageListModel>) {
        val param = HashMap<String, String>()
        param.put("uid", uid)
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        HttpHelper.getAsync(Constants.DIRECT_MESSAGES_CONVERSATION, param, callback)
    }

    fun getDirectMessages(since_id: Long = 0, max_id: Long = 0, count: Int = 20, page: Int = 1, callback: JsonCallback<DirectMessageListModel>) {
        val param = HashMap<String, String>()
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("since_id", since_id.toString())
        param.put("max_id", max_id.toString())
        HttpHelper.getAsync(Constants.DIRECT_MESSAGES, param, callback)
    }

    fun send(uid: Long, text: String, id: Long = -1, callback: JsonCallback<DirectMessageModel>) {
        val param = HashMap<String, String>()
        param.put("uid", uid.toString())
        param.put("text", text)
        if (id > 0) param.put("id", id.toString())
        HttpHelper.postAsync(Constants.DIRECT_MESSAGES_SEND, param, callback)
    }
}
