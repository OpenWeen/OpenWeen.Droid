package moe.tlaster.openween.core.model.url

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Tlaster on 2016/9/2.
 */
class ObjectModel {
    @field:JsonProperty("url")
    var url: String? = null
    @field:JsonProperty("display_name")
    var displayName: String? = null
    @field:JsonProperty("pic_ids")
    var picIds: Array<String>? = null
    @field:JsonProperty("object_type")
    var objectType: String? = null
    @field:JsonProperty("object")
    var urlObject: Any? = null
    @field:JsonProperty("original_url")
    var originalUrl: String? = null
    @field:JsonProperty("stream")
    var stream: StreamModel? = null
}
