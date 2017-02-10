package moe.tlaster.openween.core.model.status

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Tlaster on 2016/9/2.
 */
class LongTextModel {
    @field:JsonProperty("longTextContent")
    var content: String? = null
}
