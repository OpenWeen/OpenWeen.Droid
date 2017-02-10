package moe.tlaster.openween.core.model.status

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Tlaster on 2016/9/2.
 */
class GroupModel {
    @field:JsonProperty("id")
    var id: Long = 0
    @field:JsonProperty("idstr")
    var idStr: String? = null
    @field:JsonProperty("name")
    var name: String? = null
}
