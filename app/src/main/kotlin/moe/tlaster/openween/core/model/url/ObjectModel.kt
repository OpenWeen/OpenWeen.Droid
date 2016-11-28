package moe.tlaster.openween.core.model.url

import com.google.gson.annotations.SerializedName

/**
 * Created by Tlaster on 2016/9/2.
 */
class ObjectModel {
    @SerializedName("url")
    var url: String? = null
    @SerializedName("display_name")
    var displayName: String? = null
    @SerializedName("pic_ids")
    var picIds: Array<String>? = null
    @SerializedName("object_type")
    var objectType: String? = null
    @SerializedName("object")
    var urlObject: Any? = null
    @SerializedName("original_url")
    var originalUrl: String? = null
    @SerializedName("stream")
    var stream: StreamModel? = null
}
