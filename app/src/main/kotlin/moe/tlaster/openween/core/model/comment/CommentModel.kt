package moe.tlaster.openween.core.model.comment

import com.google.gson.annotations.SerializedName

import moe.tlaster.openween.core.model.BaseModel
import moe.tlaster.openween.core.model.status.MessageModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class CommentModel : BaseModel() {
    @SerializedName("source_allowclick")
    var sourceAllowClick: Int = 0
    @SerializedName("source_type")
    var sourceType: Int = 0
    @SerializedName("status")
    var status: MessageModel? = null
    @SerializedName("reply_comment")
    var replyComment: CommentModel? = null
}
