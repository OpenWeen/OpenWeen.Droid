package moe.tlaster.openween.core.model.block



import com.fasterxml.jackson.annotation.JsonProperty
import moe.tlaster.openween.core.model.user.UserModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class BlockModel {
    @field:JsonProperty("user")
    var user: UserModel? = null
    @field:JsonProperty("created_at")
    var createdAt: String? = null

}
