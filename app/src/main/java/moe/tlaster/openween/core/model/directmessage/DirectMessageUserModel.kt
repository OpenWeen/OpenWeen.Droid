package moe.tlaster.openween.core.model.directmessage



import com.fasterxml.jackson.annotation.JsonProperty
import moe.tlaster.openween.core.model.user.UserModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class DirectMessageUserModel {
    @field:JsonProperty("user")
    var user: UserModel? = null
    @field:JsonProperty("direct_message")
    var directMessage: DirectMessageModel? = null
}
