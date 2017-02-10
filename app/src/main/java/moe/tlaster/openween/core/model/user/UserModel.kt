package moe.tlaster.openween.core.model.user

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty


import org.ocpsoft.prettytime.PrettyTime

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import moe.tlaster.openween.common.helpers.TimeHelper

/**
 * Created by Tlaster on 2016/9/2.
 */
class UserModel : UserBaseModel() {
    @field:JsonProperty("screen_name")
    var screenName: String? = null
    @field:JsonProperty("name")
    var name: String? = null
    @field:JsonProperty("remark")
    var remark: String? = null
    @field:JsonProperty("province")
    var province: String? = null
    @field:JsonProperty("city")
    var city: String? = null
    @field:JsonProperty("location")
    var location: String? = null
    @field:JsonProperty("description")
    var description: String? = null
    @field:JsonProperty("url")
    var url: String? = null
    @field:JsonProperty("profile_image_url")
    var profileImageUrl: String? = null
    @field:JsonProperty("domain")
    var domain: String? = null
    @field:JsonProperty("gender")
    var gender: String? = null
    @field:JsonProperty("favourites_count")
    var favouritesCount = 0
    @field:JsonProperty("verified_type")
    var verifiedType: Int = 0
    @field:JsonProperty("created_at")
    var createdAt: String? = null
    @field:JsonProperty("following")
    var isFollowing = false
    @field:JsonProperty("allow_all_act_msg")
    var isAllowAllActMsg = false
    @field:JsonProperty("geo_enabled")
    var isGeoEnabled = false
    @field:JsonProperty("verified")
    var isVerified = false
    @field:JsonProperty("allow_all_comment")
    var isAllowAllComment = false
    @field:JsonProperty("avatar_large")
    var avatarLarge: String? = null
    @field:JsonProperty("verified_reason")
    var verifiedReason: String? = null
    @field:JsonProperty("follow_me")
    var isFollowMe = false
    @field:JsonProperty("online_status")
    var onlineStatus = 0
    @field:JsonProperty("bi_followers_count")
    var biFollowersCount = 0
    @field:JsonProperty("cover_image")
    var coverimage = ""
    @field:JsonProperty("cover_image_phone")
    var coverImagePhone: String? = null
    @field:JsonProperty("avatar_hd")
    var avatarHD: String? = null
    @field:JsonProperty("weihao")
    var weihao: String? = null
    @field:JsonProperty("lang")
    var lang: String? = null
    @field:JsonProperty("level")
    var level: Int = 0


    val createdAtDiffForHuman: String
        get() {
            try {
                return TimeHelper.prettyTime.format(TimeHelper.simpleDateFormat.parse(createdAt))
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return createdAt!!
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
