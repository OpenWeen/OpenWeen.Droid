package moe.tlaster.openween.core.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Tlaster on 2016/8/26.
 */
class LimitStatusModel {
    @SerializedName("ip_limit")
    var ipLimit: Long = 0
    @SerializedName("limit_time_unit")
    var limitTimeUnit: String? = null
    @SerializedName("remaining_ip_hits")
    var remainingIpHits: Long = 0
    @SerializedName("remaining_user_hits")
    var remainingUserHits: Int = 0
    @SerializedName("reset_time")
    var resetTimeValue: String? = null
    @SerializedName("reset_time_in_seconds")
    var resetTimeInSeconds: Long = 0
    @SerializedName("user_limit")
    var userLimit: Int = 0
    @SerializedName("error")
    var error: String? = null
    @SerializedName("error_code")
    var errorCode: String? = null
}
