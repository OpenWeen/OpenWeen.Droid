package moe.tlaster.openween.core.model.user

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Tlaster on 2016/9/2.
 */
open class UserBaseModel {
    @field:JsonProperty("id")
    var id: Long = 0
    @field:JsonProperty("idstr")
    var idStr: String? = null
    @field:JsonProperty("followers_count")
    var followersCount = 0
    @field:JsonProperty("friends_count")
    var friendsCount = 0
    @field:JsonProperty("statuses_count")
    var statusesCount = 0
}
