package moe.tlaster.openween.core.model.user

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import org.ocpsoft.prettytime.PrettyTime

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import moe.tlaster.openween.common.helpers.TimeHelper

/**
 * Created by Tlaster on 2016/9/2.
 */
class UserModel : UserBaseModel, Parcelable {
    @SerializedName("screen_name")
    var screenName: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("remark")
    var remark: String? = null
    @SerializedName("province")
    var province: String? = null
    @SerializedName("city")
    var city: String? = null
    @SerializedName("location")
    var location: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("profile_image_url")
    var profileImageUrl: String? = null
    @SerializedName("domain")
    var domain: String? = null
    @SerializedName("gender")
    var gender: String? = null
    @SerializedName("favourites_count")
    var favouritesCount = 0
    @SerializedName("verified_type")
    var verifiedType: Int = 0
    @SerializedName("created_at")
    var createdAt: String? = null
    @SerializedName("following")
    var isFollowing = false
    @SerializedName("allow_all_act_msg")
    var isAllowAllActMsg = false
    @SerializedName("geo_enabled")
    var isGeoEnabled = false
    @SerializedName("verified")
    var isVerified = false
    @SerializedName("allow_all_comment")
    var isAllowAllComment = false
    @SerializedName("avatar_large")
    var avatarLarge: String? = null
    @SerializedName("verified_reason")
    var verifiedReason: String? = null
    @SerializedName("follow_me")
    var isFollowMe = false
    @SerializedName("online_status")
    var onlineStatus = 0
    @SerializedName("bi_followers_count")
    var biFollowersCount = 0
    @SerializedName("cover_image")
    var coverimage = ""
    @SerializedName("cover_image_phone")
    var coverImagePhone: String? = null
    @SerializedName("avatar_hd")
    var avatarHD: String? = null
    @SerializedName("weihao")
    var weihao: String? = null
    @SerializedName("lang")
    var lang: String? = null
    @SerializedName("level")
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

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.screenName)
        dest.writeString(this.name)
        dest.writeString(this.remark)
        dest.writeString(this.province)
        dest.writeString(this.city)
        dest.writeString(this.location)
        dest.writeString(this.description)
        dest.writeString(this.url)
        dest.writeString(this.profileImageUrl)
        dest.writeString(this.domain)
        dest.writeString(this.gender)
        dest.writeInt(this.favouritesCount)
        dest.writeInt(this.verifiedType)
        dest.writeString(this.createdAt)
        dest.writeByte(if (this.isFollowing) 1.toByte() else 0.toByte())
        dest.writeByte(if (this.isAllowAllActMsg) 1.toByte() else 0.toByte())
        dest.writeByte(if (this.isGeoEnabled) 1.toByte() else 0.toByte())
        dest.writeByte(if (this.isVerified) 1.toByte() else 0.toByte())
        dest.writeByte(if (this.isAllowAllComment) 1.toByte() else 0.toByte())
        dest.writeString(this.avatarLarge)
        dest.writeString(this.verifiedReason)
        dest.writeByte(if (this.isFollowMe) 1.toByte() else 0.toByte())
        dest.writeInt(this.onlineStatus)
        dest.writeInt(this.biFollowersCount)
        dest.writeString(this.coverimage)
        dest.writeString(this.coverImagePhone)
        dest.writeString(this.avatarHD)
        dest.writeString(this.weihao)
        dest.writeString(this.lang)
        dest.writeInt(this.level)
        dest.writeLong(this.id)
        dest.writeString(this.idStr)
        dest.writeInt(this.followersCount)
        dest.writeInt(this.friendsCount)
        dest.writeInt(this.statusesCount)
    }

    constructor() {
    }

    protected constructor(`in`: Parcel) {
        this.screenName = `in`.readString()
        this.name = `in`.readString()
        this.remark = `in`.readString()
        this.province = `in`.readString()
        this.city = `in`.readString()
        this.location = `in`.readString()
        this.description = `in`.readString()
        this.url = `in`.readString()
        this.profileImageUrl = `in`.readString()
        this.domain = `in`.readString()
        this.gender = `in`.readString()
        this.favouritesCount = `in`.readInt()
        this.verifiedType = `in`.readInt()
        this.createdAt = `in`.readString()
        this.isFollowing = `in`.readByte().toInt() != 0
        this.isAllowAllActMsg = `in`.readByte().toInt() != 0
        this.isGeoEnabled = `in`.readByte().toInt() != 0
        this.isVerified = `in`.readByte().toInt() != 0
        this.isAllowAllComment = `in`.readByte().toInt() != 0
        this.avatarLarge = `in`.readString()
        this.verifiedReason = `in`.readString()
        this.isFollowMe = `in`.readByte().toInt() != 0
        this.onlineStatus = `in`.readInt()
        this.biFollowersCount = `in`.readInt()
        this.coverimage = `in`.readString()
        this.coverImagePhone = `in`.readString()
        this.avatarHD = `in`.readString()
        this.weihao = `in`.readString()
        this.lang = `in`.readString()
        this.level = `in`.readInt()
        this.id = `in`.readLong()
        this.idStr = `in`.readString()
        this.followersCount = `in`.readInt()
        this.friendsCount = `in`.readInt()
        this.statusesCount = `in`.readInt()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<UserModel> = object : Parcelable.Creator<UserModel> {
            override fun createFromParcel(source: Parcel): UserModel {
                return UserModel(source)
            }

            override fun newArray(size: Int): Array<UserModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
