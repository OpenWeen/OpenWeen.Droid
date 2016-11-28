package moe.tlaster.openween.core.model.block

import com.google.gson.annotations.SerializedName

/**
 * Created by Tlaster on 2016/9/2.
 */
class BlockListModel {
    @SerializedName("users")
    var users: List<BlockModel>? = null
}
