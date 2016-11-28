package moe.tlaster.openween.core.model.status

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils

import com.google.gson.annotations.SerializedName

/**
 * Created by Tlaster on 2016/9/2.
 */
class PictureModel : Parcelable {
    @SerializedName("pic_id")
    var picID: String? = null
    @SerializedName("thumbnail_pic")
    var thumbnailPic: String? = null
    @SerializedName("bmiddle_pic")
    var bmiddlePic: String? = null
    @SerializedName("original_pic")
    var originalPic: String? = null

    constructor() {

    }

    internal constructor(item: UserTimelineImageModel?) {
        if (item != null) {
            picID = item.picId
            thumbnailPic = item.thumbnail?.url
            bmiddlePic = item.bmiddle?.url
            originalPic = item.original?.url
        }
    }

    fun toLarge(): String {
        if (TextUtils.isEmpty(originalPic))
            return thumbnailPic!!.replace("thumbnail", "large")
        else
            return originalPic!!
    }

    fun toBmiddle(): String {
        return thumbnailPic!!.replace("thumbnail", "bmiddle")
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.picID)
        dest.writeString(this.thumbnailPic)
        dest.writeString(this.bmiddlePic)
        dest.writeString(this.originalPic)
    }

    protected constructor(`in`: Parcel) {
        this.picID = `in`.readString()
        this.thumbnailPic = `in`.readString()
        this.bmiddlePic = `in`.readString()
        this.originalPic = `in`.readString()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PictureModel> = object : Parcelable.Creator<PictureModel> {
            override fun createFromParcel(source: Parcel): PictureModel {
                return PictureModel(source)
            }

            override fun newArray(size: Int): Array<PictureModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
