package moe.tlaster.openween.core.model.status

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by Asahi on 2016/10/10.
 */

class UserTimelineImageModel : Parcelable {
    @SerializedName("thumbnail")
    var thumbnail: UserTimelineImageDetailModel? = null
    @SerializedName("bmiddle")
    var bmiddle: UserTimelineImageDetailModel? = null
    @SerializedName("middleplus")
    var middleplus: UserTimelineImageDetailModel? = null
    @SerializedName("large")
    var large: UserTimelineImageDetailModel? = null
    @SerializedName("original")
    var original: UserTimelineImageDetailModel? = null
    @SerializedName("largest")
    var largest: UserTimelineImageDetailModel? = null
    @SerializedName("pic_id")
    var picId: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(this.thumbnail, flags)
        dest.writeParcelable(this.bmiddle, flags)
        dest.writeParcelable(this.middleplus, flags)
        dest.writeParcelable(this.large, flags)
        dest.writeParcelable(this.original, flags)
        dest.writeParcelable(this.largest, flags)
        dest.writeString(this.picId)
    }

    constructor() {
    }

    protected constructor(`in`: Parcel) {
        this.thumbnail = `in`.readParcelable<UserTimelineImageDetailModel>(UserTimelineImageDetailModel::class.java.classLoader)
        this.bmiddle = `in`.readParcelable<UserTimelineImageDetailModel>(UserTimelineImageDetailModel::class.java.classLoader)
        this.middleplus = `in`.readParcelable<UserTimelineImageDetailModel>(UserTimelineImageDetailModel::class.java.classLoader)
        this.large = `in`.readParcelable<UserTimelineImageDetailModel>(UserTimelineImageDetailModel::class.java.classLoader)
        this.original = `in`.readParcelable<UserTimelineImageDetailModel>(UserTimelineImageDetailModel::class.java.classLoader)
        this.largest = `in`.readParcelable<UserTimelineImageDetailModel>(UserTimelineImageDetailModel::class.java.classLoader)
        this.picId = `in`.readString()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<UserTimelineImageModel> = object : Parcelable.Creator<UserTimelineImageModel> {
            override fun createFromParcel(source: Parcel): UserTimelineImageModel {
                return UserTimelineImageModel(source)
            }

            override fun newArray(size: Int): Array<UserTimelineImageModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
