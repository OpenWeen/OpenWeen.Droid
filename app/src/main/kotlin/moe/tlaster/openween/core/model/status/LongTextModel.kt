package moe.tlaster.openween.core.model.status

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by Tlaster on 2016/9/2.
 */
class LongTextModel : Parcelable {
    @SerializedName("longTextContent")
    var content: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.content)
    }

    constructor() {
    }

    protected constructor(`in`: Parcel) {
        this.content = `in`.readString()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<LongTextModel> = object : Parcelable.Creator<LongTextModel> {
            override fun createFromParcel(source: Parcel): LongTextModel {
                return LongTextModel(source)
            }

            override fun newArray(size: Int): Array<LongTextModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
