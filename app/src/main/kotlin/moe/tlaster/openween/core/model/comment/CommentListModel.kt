package moe.tlaster.openween.core.model.comment

import com.google.gson.annotations.SerializedName

import moe.tlaster.openween.core.model.BaseListModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class CommentListModel : BaseListModel() {
    @SerializedName("comments")
    var comments: List<CommentModel>? = null
}
