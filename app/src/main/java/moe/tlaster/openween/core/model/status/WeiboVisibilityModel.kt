package moe.tlaster.openween.core.model.status

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty


import moe.tlaster.openween.core.model.types.WeiboVisibility

/**
 * Created by Tlaster on 2016/9/2.
 */
class WeiboVisibilityModel {
    @field:JsonProperty("type")
    var visibility: WeiboVisibility? = null
    @field:JsonProperty("list_id")
    var listID: Int = 0
}
