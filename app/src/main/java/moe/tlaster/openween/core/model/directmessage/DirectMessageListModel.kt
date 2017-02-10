package moe.tlaster.openween.core.model.directmessage



import com.fasterxml.jackson.annotation.JsonProperty
import moe.tlaster.openween.core.model.BaseListModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class DirectMessageListModel : BaseListModel() {
    @field:JsonProperty("direct_messages")
    var directMessages: List<DirectMessageModel>? = null

}
