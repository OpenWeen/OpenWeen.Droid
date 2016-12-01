package moe.tlaster.openween.core.model.attitude

import com.google.gson.annotations.SerializedName
import moe.tlaster.openween.core.model.BaseListModel
import moe.tlaster.openween.core.model.status.MessageModel

/**
 * Created by Asahi on 16/12/01.
 */
class AttitudeListModel : BaseListModel() {
    @SerializedName("attitudes")
    var attitudes: List<AttitudeModel>? = null
}