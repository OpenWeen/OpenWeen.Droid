package moe.tlaster.openween.core.model.status

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Asahi on 2016/10/10.
 */

class UserTimelineImageDetailModel {
    @field:JsonProperty("url")
    var url: String? = null
    @field:JsonProperty("width")
    var width: Int = 0
    @field:JsonProperty("height")
    var height: Int = 0
}
