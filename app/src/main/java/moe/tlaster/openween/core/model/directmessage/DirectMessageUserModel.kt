package moe.tlaster.openween.core.model.directmessage

import com.google.gson.annotations.SerializedName

import moe.tlaster.openween.core.model.user.UserModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class DirectMessageUserModel {
    @SerializedName("user")
    var user: UserModel? = null
    @SerializedName("direct_message")
    var directMessage: DirectMessageModel? = null
}
