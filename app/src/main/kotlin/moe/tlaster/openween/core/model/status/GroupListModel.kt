package moe.tlaster.openween.core.model.status

import com.google.gson.annotations.SerializedName

import moe.tlaster.openween.core.model.BaseListModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class GroupListModel : BaseListModel() {
    @SerializedName("lists")
    var lists: List<GroupModel>? = null
}
