package moe.tlaster.openween.core.model

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Tlaster on 2016/8/26.
 */
class EmotionModel {
    @field:JsonProperty("phrase")
    var phrase: String? = null
    @field:JsonProperty("type")
    var type: String? = null
    @field:JsonProperty("url")
    var url: String? = null
    @field:JsonProperty("hot")
    var isHot: Boolean = false
    @field:JsonProperty("common")
    var isCommon: Boolean = false
    @field:JsonProperty("category")
    var category: String? = null
    @field:JsonProperty("icon")
    var icon: String? = null
    @field:JsonProperty("value")
    var value: String? = null
    @field:JsonProperty("picid")
    var picID: String? = null
}
