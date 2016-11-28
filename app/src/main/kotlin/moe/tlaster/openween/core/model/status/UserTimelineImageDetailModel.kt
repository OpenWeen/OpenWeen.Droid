package moe.tlaster.openween.core.model.status

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by Asahi on 2016/10/10.
 */

class UserTimelineImageDetailModel : Parcelable {
    @SerializedName("url")
    var url: String? = null
    @SerializedName("width")
    var width: Int = 0
    @SerializedName("height")
    var height: Int = 0

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.url)
        dest.writeInt(this.width)
        dest.writeInt(this.height)
    }

    constructor() {
    }

    protected constructor(`in`: Parcel) {
        this.url = `in`.readString()
        this.width = `in`.readInt()
        this.height = `in`.readInt()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<UserTimelineImageDetailModel> = object : Parcelable.Creator<UserTimelineImageDetailModel> {
            override fun createFromParcel(source: Parcel): UserTimelineImageDetailModel {
                return UserTimelineImageDetailModel(source)
            }

            override fun newArray(size: Int): Array<UserTimelineImageDetailModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
