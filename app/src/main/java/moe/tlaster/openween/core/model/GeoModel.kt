package moe.tlaster.openween.core.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Tlaster on 2016/8/26.
 */
class GeoModel {
    @field:JsonProperty("longitude")
    var longitude: String? = null
    @field:JsonProperty("latitude")
    var latitude: String? = null
    @field:JsonProperty("city")
    var city: String? = null
    @field:JsonProperty("province")
    var province: String? = null
    @field:JsonProperty("city_name")
    var cityName: String? = null
    @field:JsonProperty("province_name")
    var provinceName: String? = null
    @field:JsonProperty("address")
    var address: String? = null
    @field:JsonProperty("pinyin")
    var pinyin: String? = null
    @field:JsonProperty("more")
    var more: String? = null
}
