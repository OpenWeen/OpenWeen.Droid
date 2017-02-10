package moe.tlaster.openween.core.model.favor

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Tlaster on 2016/9/2.
 */
class FavorTagListModel {
    @field:JsonProperty("tags")
    var tags: List<FavorTagModel>? = null
    @field:JsonProperty("total_number")
    var totalNumber: Int = 0
}
