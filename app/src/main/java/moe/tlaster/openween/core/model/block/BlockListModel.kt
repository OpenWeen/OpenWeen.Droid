package moe.tlaster.openween.core.model.block

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Tlaster on 2016/9/2.
 */
class BlockListModel {
    @field:JsonProperty("users")
    var users: List<BlockModel>? = null
}
