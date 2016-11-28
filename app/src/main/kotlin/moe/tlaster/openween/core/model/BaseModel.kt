package moe.tlaster.openween.core.model

import android.os.Parcelable

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.google.gson.annotations.SerializedName

import org.ocpsoft.prettytime.PrettyTime

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
    @SerializedName("id")
    var id: Long = 0
    @SerializedName("mid")
    var mid: Long = 0
    @SerializedName("idstr")
    var idStr: String = ""
    @SerializedName("created_at")
    var createdAt: String = ""
    @SerializedName("text")
    var text: String = ""
    @SerializedName("source")
    var source: String = ""
    @SerializedName("user")
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
