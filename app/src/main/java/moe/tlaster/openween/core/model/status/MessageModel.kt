package moe.tlaster.openween.core.model.status

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty


import java.util.ArrayList
import android.support.v4.util.ArrayMap

import moe.tlaster.openween.core.model.BaseModel
import moe.tlaster.openween.core.model.GeoModel
import moe.tlaster.openween.core.model.user.UserModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class MessageModel : BaseModel() {
    @field:JsonProperty("favorited")
    var isFavorited: Boolean = false
    @field:JsonProperty("truncated")
    var isTruncated: Boolean = false
    @field:JsonProperty("liked")
    var isLiked: Boolean = false
    @field:JsonProperty("in_reply_to_status_id")
    var inReplyToStatusID: String? = null
    @field:JsonProperty("in_reply_to_user_id")
    var inReplyToUserID: String? = null
    @field:JsonProperty("in_reply_to_screen_name")
    var inReplyToScreenName: String? = null
    @field:JsonProperty("thumbnail_pic")
    var thumbnailPic: String? = null
    @field:JsonProperty("bmiddle_pic")
    var bmiddlePic: String? = null
    @field:JsonProperty("original_pic")
    var originalPic: String? = null
    @field:JsonProperty("geo")
    var geo: GeoModel? = null
    @field:JsonProperty("retweeted_status")
    var retweetedStatus: MessageModel? = null
    @field:JsonProperty("reposts_count")
    var repostsCount: Int = 0
    @field:JsonProperty("comments_count")
    var commentsCount: Int = 0
    @field:JsonProperty("attitudes_count")
    var attitudesCount: Int = 0
    @field:JsonProperty("mlevel")
    var level: Int = 0
    @field:JsonProperty("longText")
    var longText: LongTextModel? = null
    @field:JsonProperty("visible")
    var visible: WeiboVisibilityModel? = null
    @field:JsonProperty("pic_urls")
    private var mPicUrls: List<PictureModel>? = null
    @field:JsonProperty("pic_infos")
    private var userTimelineImage: MutableMap<String, UserTimelineImageModel>? = null


    var picUrls: List<PictureModel>?
        get() = if (mPicUrls == null && userTimelineImage != null)
            userTimelineImage!!.values.map(::PictureModel)
        else
            mPicUrls
        set(picUrls) {
            mPicUrls = picUrls
        }
}
