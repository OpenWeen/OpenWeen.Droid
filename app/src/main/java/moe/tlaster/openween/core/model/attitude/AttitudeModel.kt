package moe.tlaster.openween.core.model.attitude


import com.fasterxml.jackson.annotation.JsonProperty
import moe.tlaster.openween.core.model.BaseModel
import moe.tlaster.openween.core.model.status.MessageModel

/**
 * Created by Asahi on 16/12/01.
 */
class AttitudeModel : BaseModel() {
    @field:JsonProperty("attitude")
    var attitude: String = ""
    @field:JsonProperty("attitude_type")
    var attitudeType: Int = -1
    @field:JsonProperty("last_attitude")
    var lastAttitude: String = ""
    @field:JsonProperty("status")
    var status: MessageModel? = null
}