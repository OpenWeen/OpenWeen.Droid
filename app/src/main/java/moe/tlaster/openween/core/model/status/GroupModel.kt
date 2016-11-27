package moe.tlaster.openween.core.model.status

import com.google.gson.annotations.SerializedName

/**
 * Created by Tlaster on 2016/9/2.
 */
class GroupModel {
    @SerializedName("id")
    var id: Long = 0
    @SerializedName("idstr")
    var idStr: String? = null
    @SerializedName("name")
    var name: String? = null
}
