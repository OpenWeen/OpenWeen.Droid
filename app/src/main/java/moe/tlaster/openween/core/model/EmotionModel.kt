package moe.tlaster.openween.core.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Tlaster on 2016/8/26.
 */
class EmotionModel {
    @SerializedName("phrase")
    var phrase: String? = null
    @SerializedName("type")
    var type: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("hot")
    var isHot: Boolean = false
    @SerializedName("common")
    var isCommon: Boolean = false
    @SerializedName("category")
    var category: String? = null
    @SerializedName("icon")
    var icon: String? = null
    @SerializedName("value")
    var value: String? = null
    @SerializedName("picid")
    var picID: String? = null
}
