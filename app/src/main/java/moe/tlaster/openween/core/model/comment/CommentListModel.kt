package moe.tlaster.openween.core.model.comment



import com.fasterxml.jackson.annotation.JsonProperty
import moe.tlaster.openween.core.model.BaseListModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class CommentListModel : BaseListModel() {
    @field:JsonProperty("comments")
    var comments: List<CommentModel>? = null
}
