package moe.tlaster.openween.core.model.status

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Asahi on 2016/10/10.
 */

class UserTimelineImageModel {
    @field:JsonProperty("thumbnail")
    var thumbnail: UserTimelineImageDetailModel? = null
    @field:JsonProperty("bmiddle")
    var bmiddle: UserTimelineImageDetailModel? = null
    @field:JsonProperty("middleplus")
    var middleplus: UserTimelineImageDetailModel? = null
    @field:JsonProperty("large")
    var large: UserTimelineImageDetailModel? = null
    @field:JsonProperty("original")
    var original: UserTimelineImageDetailModel? = null
    @field:JsonProperty("largest")
    var largest: UserTimelineImageDetailModel? = null
    @field:JsonProperty("pic_id")
    var picId: String? = null
}
