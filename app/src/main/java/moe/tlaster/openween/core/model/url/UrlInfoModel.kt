package moe.tlaster.openween.core.model.url

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Tlaster on 2016/9/2.
 */
class UrlInfoModel {
    @field:JsonProperty("result")
    var isResult: Boolean = false
    @field:JsonProperty("last_modified")
    var lastModified: Long = 0
    @field:JsonProperty("title")
    var title: String? = null
    @field:JsonProperty("description")
    var description: String? = null
    @field:JsonProperty("url_short")
    var urlShort: String? = null
    @field:JsonProperty("url_long")
    var urlLong: String? = null
    @field:JsonProperty("annotations")
    var annotations: List<AnnotationModel>? = null
    @field:JsonProperty("type")
    var type: Int = 0
}