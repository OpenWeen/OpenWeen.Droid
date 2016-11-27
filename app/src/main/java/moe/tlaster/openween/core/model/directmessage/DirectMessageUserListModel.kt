package moe.tlaster.openween.core.model.directmessage

import com.google.gson.annotations.SerializedName

import moe.tlaster.openween.core.model.BaseListModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class DirectMessageUserListModel : BaseListModel() {
    @SerializedName("user_list")
    var userList: List<DirectMessageUserModel>? = null
    @SerializedName("totalNumber")
    override var totalNumber: Int = 0
}
