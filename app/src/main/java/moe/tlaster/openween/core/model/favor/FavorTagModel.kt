package moe.tlaster.openween.core.model.favor

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Tlaster on 2016/9/2.
 */
class FavorTagModel {
    @field:JsonProperty("id")
    var id: Long = 0
    @field:JsonProperty("tag")
    var tag: String? = null
}
