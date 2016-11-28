package moe.tlaster.openween.core.model.url


import com.google.gson.annotations.SerializedName

/**
 * Created by Tlaster on 2016/9/2.
 */
class UrlInfoModel {
    @SerializedName("result")
    var isResult: Boolean = false
    @SerializedName("last_modified")
    var lastModified: Long = 0
    @SerializedName("title")
    var title: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("url_short")
    var urlShort: String? = null
    @SerializedName("url_long")
    var urlLong: String? = null
    @SerializedName("annotations")
    var annotations: List<AnnotationModel>? = null
    @SerializedName("type")
    var type: Int = 0
}