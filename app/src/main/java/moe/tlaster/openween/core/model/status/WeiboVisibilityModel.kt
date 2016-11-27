package moe.tlaster.openween.core.model.status

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import moe.tlaster.openween.core.model.types.WeiboVisibility

/**
 * Created by Tlaster on 2016/9/2.
 */
class WeiboVisibilityModel : Parcelable {
    @SerializedName("type")
    var visibility: WeiboVisibility? = null
    @SerializedName("list_id")
    var listID: Int = 0

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(if (this.visibility == null) -1 else this.visibility!!.ordinal)
        dest.writeInt(this.listID)
    }

    constructor() {
    }

    protected constructor(`in`: Parcel) {
        val tmpMVisibility = `in`.readInt()
        this.visibility = if (tmpMVisibility == -1) null else WeiboVisibility.values()[tmpMVisibility]
        this.listID = `in`.readInt()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<WeiboVisibilityModel> = object : Parcelable.Creator<WeiboVisibilityModel> {
            override fun createFromParcel(source: Parcel): WeiboVisibilityModel {
                return WeiboVisibilityModel(source)
            }

            override fun newArray(size: Int): Array<WeiboVisibilityModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
