package moe.tlaster.openween.core.model

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Tlaster on 2016/8/26.
 */
class LimitStatusModel {
    @field:JsonProperty("ip_limit")
    var ipLimit: Long = 0
    @field:JsonProperty("limit_time_unit")
    var limitTimeUnit: String? = null
    @field:JsonProperty("remaining_ip_hits")
    var remainingIpHits: Long = 0
    @field:JsonProperty("remaining_user_hits")
    var remainingUserHits: Int = 0
    @field:JsonProperty("reset_time")
    var resetTimeValue: String? = null
    @field:JsonProperty("reset_time_in_seconds")
    var resetTimeInSeconds: Long = 0
    @field:JsonProperty("user_limit")
    var userLimit: Int = 0
    @field:JsonProperty("error")
    var error: String? = null
    @field:JsonProperty("error_code")
    var errorCode: String? = null
}
