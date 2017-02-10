package moe.tlaster.openween.core.model.status



import com.fasterxml.jackson.annotation.JsonProperty
import moe.tlaster.openween.core.model.BaseListModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class GroupListModel : BaseListModel() {
    @field:JsonProperty("lists")
    var lists: List<GroupModel>? = null
}
