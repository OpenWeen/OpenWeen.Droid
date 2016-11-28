package moe.tlaster.openween.core.model.directmessage

import com.google.gson.annotations.SerializedName

import moe.tlaster.openween.core.model.BaseListModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class DirectMessageListModel : BaseListModel() {
    @SerializedName("direct_messages")
    var directMessages: List<DirectMessageModel>? = null

}
