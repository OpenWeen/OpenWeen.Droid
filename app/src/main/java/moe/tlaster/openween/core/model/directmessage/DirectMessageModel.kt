package moe.tlaster.openween.core.model.directmessage



import com.fasterxml.jackson.annotation.JsonProperty
import moe.tlaster.openween.core.model.user.UserModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class DirectMessageModel {
    @field:JsonProperty("id")
    var id: Long = 0
    @field:JsonProperty("idstr")
    var idStr: String? = null
    @field:JsonProperty("created_at")
    var createdAt: String? = null
    @field:JsonProperty("text")
    var text: String? = null
    @field:JsonProperty("sender_id")
    var senderID: Long = 0
    @field:JsonProperty("recipient_id")
    var recipientID: Long = 0
    @field:JsonProperty("recipient")
    var recipient: UserModel? = null
    @field:JsonProperty("sender")
    var sender: UserModel? = null
    @field:JsonProperty("sender_screen_name")
    var senderScreenName: String? = null
    @field:JsonProperty("recipient_screen_name")
    var recipientScreenName: String? = null
    @field:JsonProperty("att_ids")
    var attIDs = longArrayOf(0, 0)
}
