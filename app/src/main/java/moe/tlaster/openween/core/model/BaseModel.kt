package moe.tlaster.openween.core.model

import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import moe.tlaster.openween.common.helpers.TimeHelper
import moe.tlaster.openween.core.model.user.UserModel

/**
 * Created by Tlaster on 2016/8/26.
 */
abstract class BaseModel {
    @field:JsonProperty("id")
    var id: Long = 0
    @field:JsonProperty("mid")
    var mid: Long = 0
    @field:JsonProperty("idstr")
    var idStr: String = ""
    @field:JsonProperty("created_at")
    var createdAt: String = ""
    @field:JsonProperty("text")
    var text: String = ""
    @field:JsonProperty("source")
    var source: String = ""
    @field:JsonProperty("user")
    var user: UserModel? = null
    val createdAtDiffForHuman: String
        get() {
            try {
                return TimeHelper.prettyTime.format(TimeHelper.simpleDateFormat.parse(createdAt))
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return createdAt
        }

    val createdDate: Date?
        get() {
            try {
                return TimeHelper.simpleDateFormat.parse(createdAt)
            } catch (e: ParseException) {
                return null
            }

        }


}
