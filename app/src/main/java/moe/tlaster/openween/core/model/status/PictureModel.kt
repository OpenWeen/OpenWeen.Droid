package moe.tlaster.openween.core.model.status

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Tlaster on 2016/9/2.
 */
class PictureModel {
    @field:JsonProperty("pic_id")
    var picID: String? = null
    @field:JsonProperty("thumbnail_pic")
    var thumbnailPic: String? = null
    @field:JsonProperty("bmiddle_pic")
    var bmiddlePic: String? = null
    @field:JsonProperty("original_pic")
    var originalPic: String? = null
    internal constructor(item: UserTimelineImageModel?) {
        if (item != null) {
            picID = item.picId
            thumbnailPic = item.thumbnail?.url
            bmiddlePic = item.bmiddle?.url
            originalPic = item.original?.url
        }
    }
}
