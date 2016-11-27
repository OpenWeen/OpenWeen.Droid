package moe.tlaster.openween.core.model.directmessage

import com.google.gson.annotations.SerializedName

import moe.tlaster.openween.core.model.user.UserModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class DirectMessageModel {
    @SerializedName("id")
    var id: Long = 0
    @SerializedName("idstr")
    var idStr: String? = null
    @SerializedName("created_at")
    var createdAt: String? = null
    @SerializedName("text")
    var text: String? = null
    @SerializedName("sender_id")
    var senderID: Long = 0
    @SerializedName("recipient_id")
    var recipientID: Long = 0
    @SerializedName("recipient")
    var recipient: UserModel? = null
    @SerializedName("sender")
    var sender: UserModel? = null
    @SerializedName("sender_screen_name")
    var senderScreenName: String? = null
    @SerializedName("recipient_screen_name")
    var recipientScreenName: String? = null
    @SerializedName("att_ids")
    var attIDs = longArrayOf(0, 0)
}
