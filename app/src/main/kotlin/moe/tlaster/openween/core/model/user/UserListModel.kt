package moe.tlaster.openween.core.model.user

import com.google.gson.annotations.SerializedName

import moe.tlaster.openween.core.model.BaseListModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class UserListModel : BaseListModel() {
    @SerializedName("users")
    var users: List<UserModel>? = null
}
