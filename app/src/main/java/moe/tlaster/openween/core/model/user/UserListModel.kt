package moe.tlaster.openween.core.model.user



import com.fasterxml.jackson.annotation.JsonProperty
import moe.tlaster.openween.core.model.BaseListModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class UserListModel : BaseListModel() {
    @field:JsonProperty("users")
    var users: List<UserModel>? = null
}
