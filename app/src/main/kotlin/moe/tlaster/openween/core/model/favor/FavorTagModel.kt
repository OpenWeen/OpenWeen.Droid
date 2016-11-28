package moe.tlaster.openween.core.model.favor

import com.google.gson.annotations.SerializedName

/**
 * Created by Tlaster on 2016/9/2.
 */
class FavorTagModel {
    @SerializedName("id")
    var id: Long = 0
    @SerializedName("tag")
    var tag: String? = null
}
