package moe.tlaster.openween.core.model.favor

import com.google.gson.annotations.SerializedName

import moe.tlaster.openween.core.model.status.MessageModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class FavorModel {
    @SerializedName("status")
    var status: MessageModel? = null
    @SerializedName("favorited_time")
    var favoritedTime: String? = null
    @SerializedName("tags")
    var tags: List<FavorTagModel>? = null
}
