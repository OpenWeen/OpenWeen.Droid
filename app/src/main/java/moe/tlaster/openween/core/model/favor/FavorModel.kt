package moe.tlaster.openween.core.model.favor



import com.fasterxml.jackson.annotation.JsonProperty
import moe.tlaster.openween.core.model.status.MessageModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class FavorModel {
    @field:JsonProperty("status")
    var status: MessageModel? = null
    @field:JsonProperty("favorited_time")
    var favoritedTime: String? = null
    @field:JsonProperty("tags")
    var tags: List<FavorTagModel>? = null
}
