package moe.tlaster.openween.core.model.block

import com.google.gson.annotations.SerializedName

import moe.tlaster.openween.core.model.user.UserModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class BlockModel {
    @SerializedName("user")
    var user: UserModel? = null
    @SerializedName("created_at")
    var createdAt: String? = null

}
