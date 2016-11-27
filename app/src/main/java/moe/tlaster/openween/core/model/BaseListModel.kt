package moe.tlaster.openween.core.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Tlaster on 2016/8/26.
 */
open class BaseListModel {
    @SerializedName("previous_cursor")
    var previousCursor: String? = null
    @SerializedName("next_cursor")
    var nextCursor: String? = null
    @SerializedName("total_number")
    open var totalNumber: Int = 0
}
