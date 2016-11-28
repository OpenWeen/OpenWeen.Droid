package moe.tlaster.openween.core.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by Tlaster on 2016/8/26.
 */
class GeoModel : Parcelable {
    @SerializedName("longitude")
    var longitude: String? = null
    @SerializedName("latitude")
    var latitude: String? = null
    @SerializedName("city")
    var city: String? = null
    @SerializedName("province")
    var province: String? = null
    @SerializedName("city_name")
    var cityName: String? = null
    @SerializedName("province_name")
    var provinceName: String? = null
    @SerializedName("address")
    var address: String? = null
    @SerializedName("pinyin")
    var pinyin: String? = null
    @SerializedName("more")
    var more: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.longitude)
        dest.writeString(this.latitude)
        dest.writeString(this.city)
        dest.writeString(this.province)
        dest.writeString(this.cityName)
        dest.writeString(this.provinceName)
        dest.writeString(this.address)
        dest.writeString(this.pinyin)
        dest.writeString(this.more)
    }

    constructor() {
    }

    protected constructor(`in`: Parcel) {
        this.longitude = `in`.readString()
        this.latitude = `in`.readString()
        this.city = `in`.readString()
        this.province = `in`.readString()
        this.cityName = `in`.readString()
        this.provinceName = `in`.readString()
        this.address = `in`.readString()
        this.pinyin = `in`.readString()
        this.more = `in`.readString()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<GeoModel> = object : Parcelable.Creator<GeoModel> {
            override fun createFromParcel(source: Parcel): GeoModel {
                return GeoModel(source)
            }

            override fun newArray(size: Int): Array<GeoModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
