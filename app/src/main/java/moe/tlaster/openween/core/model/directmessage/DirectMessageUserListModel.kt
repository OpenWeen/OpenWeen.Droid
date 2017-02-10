package moe.tlaster.openween.core.model.directmessage



import com.fasterxml.jackson.annotation.JsonProperty
import moe.tlaster.openween.core.model.BaseListModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class DirectMessageUserListModel : BaseListModel() {
    @field:JsonProperty("user_list")
    var userList: List<DirectMessageUserModel>? = null
    @field:JsonProperty("totalNumber")
    override var totalNumber: Int = 0
}
