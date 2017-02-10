package moe.tlaster.openween.core.model.comment



import com.fasterxml.jackson.annotation.JsonProperty
import moe.tlaster.openween.core.model.BaseModel
import moe.tlaster.openween.core.model.status.MessageModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class CommentModel : BaseModel() {
    @field:JsonProperty("source_allowclick")
    var sourceAllowClick: Int = 0
    @field:JsonProperty("source_type")
    var sourceType: Int = 0
    @field:JsonProperty("status")
    var status: MessageModel? = null
    @field:JsonProperty("reply_comment")
    var replyComment: CommentModel? = null
}
