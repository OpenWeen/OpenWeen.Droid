package moe.tlaster.openween.core.model.user

import com.google.gson.annotations.SerializedName

/**
 * Created by Tlaster on 2016/9/2.
 */
open class UserBaseModel {
    @SerializedName("id")
    var id: Long = 0
    @SerializedName("idstr")
    var idStr: String? = null
    @SerializedName("followers_count")
    var followersCount = 0
    @SerializedName("friends_count")
    var friendsCount = 0
    @SerializedName("statuses_count")
    var statusesCount = 0
}
