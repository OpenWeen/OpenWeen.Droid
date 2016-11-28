package moe.tlaster.openween.core.model.status

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import java.util.ArrayList
import java.util.HashMap

import moe.tlaster.openween.core.model.BaseModel
import moe.tlaster.openween.core.model.GeoModel
import moe.tlaster.openween.core.model.user.UserModel

/**
 * Created by Tlaster on 2016/9/2.
 */
class MessageModel : BaseModel, Parcelable {
    @SerializedName("favorited")
    var isFavorited: Boolean = false
    @SerializedName("truncated")
    var isTruncated: Boolean = false
    @SerializedName("liked")
    var isLiked: Boolean = false
    @SerializedName("in_reply_to_status_id")
    var inReplyToStatusID: String? = null
    @SerializedName("in_reply_to_user_id")
    var inReplyToUserID: String? = null
    @SerializedName("in_reply_to_screen_name")
    var inReplyToScreenName: String? = null
    @SerializedName("thumbnail_pic")
    var thumbnailPic: String? = null
    @SerializedName("bmiddle_pic")
    var bmiddlePic: String? = null
    @SerializedName("original_pic")
    var originalPic: String? = null
    @SerializedName("geo")
    var geo: GeoModel? = null
    @SerializedName("retweeted_status")
    var retweetedStatus: MessageModel? = null
    @SerializedName("reposts_count")
    var repostsCount: Int = 0
    @SerializedName("comments_count")
    var commentsCount: Int = 0
    @SerializedName("attitudes_count")
    var attitudesCount: Int = 0
    @SerializedName("mlevel")
    var level: Int = 0
    @SerializedName("longText")
    var longText: LongTextModel? = null
    @SerializedName("visible")
    var visible: WeiboVisibilityModel? = null
    @SerializedName("pic_urls")
    private var mPicUrls: List<PictureModel>? = null
    @SerializedName("pic_infos")
    private var userTimelineImage: MutableMap<String, UserTimelineImageModel>? = null


    var picUrls: List<PictureModel> ?
        get() = if (mPicUrls == null && userTimelineImage != null)
            userTimelineImage!!.values.map(::PictureModel)
        else
            mPicUrls
        set(picUrls) {
            mPicUrls = picUrls
        }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeByte(if (this.isFavorited) 1.toByte() else 0.toByte())
        dest.writeByte(if (this.isTruncated) 1.toByte() else 0.toByte())
        dest.writeByte(if (this.isLiked) 1.toByte() else 0.toByte())
        dest.writeString(this.inReplyToStatusID)
        dest.writeString(this.inReplyToUserID)
        dest.writeString(this.inReplyToScreenName)
        dest.writeString(this.thumbnailPic)
        dest.writeString(this.bmiddlePic)
        dest.writeString(this.originalPic)
        dest.writeParcelable(this.geo, flags)
        dest.writeParcelable(this.retweetedStatus, flags)
        dest.writeInt(this.repostsCount)
        dest.writeInt(this.commentsCount)
        dest.writeInt(this.attitudesCount)
        dest.writeInt(this.level)
        dest.writeParcelable(this.longText, flags)
        dest.writeParcelable(this.visible, flags)
        dest.writeList(this.mPicUrls)
        if (userTimelineImage != null) {
            dest.writeInt(this.userTimelineImage!!.size)
            for ((key, value) in this.userTimelineImage!!) {
                dest.writeString(key)
                dest.writeParcelable(value, flags)
            }
        } else {
            dest.writeInt(-1)
        }
        dest.writeLong(this.id)
        dest.writeLong(this.mid)
        dest.writeString(this.idStr)
        dest.writeString(this.createdAt)
        dest.writeString(this.text)
        dest.writeString(this.source)
        dest.writeParcelable(this.user, flags)
    }

    constructor() {
    }

    protected constructor(`in`: Parcel) {
        this.isFavorited = `in`.readByte().toInt() != 0
        this.isTruncated = `in`.readByte().toInt() != 0
        this.isLiked = `in`.readByte().toInt() != 0
        this.inReplyToStatusID = `in`.readString()
        this.inReplyToUserID = `in`.readString()
        this.inReplyToScreenName = `in`.readString()
        this.thumbnailPic = `in`.readString()
        this.bmiddlePic = `in`.readString()
        this.originalPic = `in`.readString()
        this.geo = `in`.readParcelable<GeoModel>(GeoModel::class.java.classLoader)
        this.retweetedStatus = `in`.readParcelable<MessageModel>(MessageModel::class.java.classLoader)
        this.repostsCount = `in`.readInt()
        this.commentsCount = `in`.readInt()
        this.attitudesCount = `in`.readInt()
        this.level = `in`.readInt()
        this.longText = `in`.readParcelable<LongTextModel>(LongTextModel::class.java.classLoader)
        this.visible = `in`.readParcelable<WeiboVisibilityModel>(WeiboVisibilityModel::class.java.classLoader)
        this.mPicUrls = ArrayList<PictureModel>()
        `in`.readList(this.mPicUrls, PictureModel::class.java.classLoader)
        val mUserTimelineImageSize = `in`.readInt()
        if (mUserTimelineImageSize != -1) {
            this.userTimelineImage = HashMap<String, UserTimelineImageModel>(mUserTimelineImageSize)
            for (i in 0..mUserTimelineImageSize - 1) {
                val key = `in`.readString()
                val value = `in`.readParcelable<UserTimelineImageModel>(UserTimelineImageModel::class.java.classLoader)
                this.userTimelineImage!!.put(key, value)
            }
        }
        this.id = `in`.readLong()
        this.mid = `in`.readLong()
        this.idStr = `in`.readString()
        this.createdAt = `in`.readString()
        this.text = `in`.readString()
        this.source = `in`.readString()
        this.user = `in`.readParcelable<UserModel>(UserModel::class.java.classLoader)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MessageModel> = object : Parcelable.Creator<MessageModel> {
            override fun createFromParcel(source: Parcel): MessageModel {
                return MessageModel(source)
            }

            override fun newArray(size: Int): Array<MessageModel?> {
                return kotlin.arrayOfNulls(size)
            }
        }
    }
}
