package moe.tlaster.openween.core.model.attitude

import com.google.gson.annotations.SerializedName
import moe.tlaster.openween.core.model.BaseModel
import moe.tlaster.openween.core.model.status.MessageModel

/**
 * Created by Asahi on 16/12/01.
 */
class AttitudeModel : BaseModel() {
    @SerializedName("attitude")
    var attitude: String = ""
    @SerializedName("attitude_type")
    var attitudeType: Int = -1
    @SerializedName("last_attitude")
    var lastAttitude: String = ""
    @SerializedName("status")
    var status: MessageModel? = null
}