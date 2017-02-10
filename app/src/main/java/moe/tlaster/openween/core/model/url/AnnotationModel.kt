package moe.tlaster.openween.core.model.url

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Tlaster on 2016/9/2.
 */
class AnnotationModel {
    @field:JsonProperty("object")
    var item: ObjectModel? = null
}
