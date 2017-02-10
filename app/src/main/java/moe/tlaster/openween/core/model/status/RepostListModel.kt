package moe.tlaster.openween.core.model.status



import com.fasterxml.jackson.annotation.JsonProperty
import moe.tlaster.openween.core.model.BaseListModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class RepostListModel : BaseListModel() {
    @field:JsonProperty("reposts")
    var reposts: List<MessageModel>? = null
}
