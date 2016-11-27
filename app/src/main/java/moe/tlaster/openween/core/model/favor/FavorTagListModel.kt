package moe.tlaster.openween.core.model.favor

import com.google.gson.annotations.SerializedName

/**
 * Created by Tlaster on 2016/9/2.
 */
class FavorTagListModel {
    @SerializedName("tags")
    var tags: List<FavorTagModel>? = null
    @SerializedName("total_number")
    var totalNumber: Int = 0
}
