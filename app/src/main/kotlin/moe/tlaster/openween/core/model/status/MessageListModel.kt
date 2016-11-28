package moe.tlaster.openween.core.model.status

import com.google.gson.annotations.SerializedName

import moe.tlaster.openween.core.model.BaseListModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class MessageListModel : BaseListModel() {
    @SerializedName("statuses")
    var statuses: List<MessageModel>? = null
}
