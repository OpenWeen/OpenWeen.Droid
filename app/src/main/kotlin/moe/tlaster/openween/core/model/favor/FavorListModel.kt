package moe.tlaster.openween.core.model.favor

import com.google.gson.annotations.SerializedName

/**
 * Created by Tlaster on 2016/9/2.
 */
class FavorListModel {
    @SerializedName("favorites")
    var favorites: List<FavorModel>? = null
    @SerializedName("total_number")
    var totalNumber: Int = 0
}
