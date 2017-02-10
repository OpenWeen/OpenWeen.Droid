package moe.tlaster.openween.core.model

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Tlaster on 2016/8/26.
 */
open class BaseListModel {
    @field:JsonProperty("previous_cursor")
    var previousCursor: String? = null
    @field:JsonProperty("next_cursor")
    var nextCursor: String? = null
    @field:JsonProperty("total_number")
    open var totalNumber: Int = 0
}
