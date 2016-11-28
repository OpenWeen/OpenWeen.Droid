package moe.tlaster.openween.core.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Tlaster on 2016/8/26.
 */
class UnreadModel {
    @SerializedName("status")
    var status = 0
    @SerializedName("follower")
    var follower = 0
    @SerializedName("cmt")
    var cmt = 0
    @SerializedName("dm")
    var dm = 0
    @SerializedName("mention_status")
    var mentionStatus = 0
    @SerializedName("mention_cmt")
    var mentionCmt = 0
    @SerializedName("group")
    var group = 0
    @SerializedName("private_group")
    var privateGroup = 0
    @SerializedName("notice")
    var notice = 0
    @SerializedName("invite")
    var invite = 0
    @SerializedName("badge")
    var badge = 0
    @SerializedName("photo")
    var photo = 0
    @SerializedName("msgbox")
    var msgBox = 0

    override fun equals(obj: Any?): Boolean {
        if (obj !is UnreadModel) return false
        return obj.status == status &&
                obj.follower == follower &&
                obj.cmt == cmt &&
                obj.dm == dm &&
                obj.mentionStatus == mentionStatus &&
                obj.mentionCmt == mentionCmt &&
                obj.group == group &&
                obj.privateGroup == privateGroup &&
                obj.notice == notice &&
                obj.invite == invite &&
                obj.badge == badge &&
                obj.photo == photo &&
                obj.msgBox == msgBox
    }
}
