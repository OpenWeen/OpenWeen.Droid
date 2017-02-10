package moe.tlaster.openween.core.model

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Tlaster on 2016/8/26.
 */
class UnreadModel {
    @field:JsonProperty("attitude")
    var attitude = 0
    @field:JsonProperty("status")
    var status = 0
    @field:JsonProperty("follower")
    var follower = 0
    @field:JsonProperty("cmt")
    var cmt = 0
    @field:JsonProperty("dm")
    var dm = 0
    @field:JsonProperty("mention_status")
    var mentionStatus = 0
    @field:JsonProperty("mention_cmt")
    var mentionCmt = 0
    @field:JsonProperty("group")
    var group = 0
    @field:JsonProperty("private_group")
    var privateGroup = 0
    @field:JsonProperty("notice")
    var notice = 0
    @field:JsonProperty("invite")
    var invite = 0
    @field:JsonProperty("badge")
    var badge = 0
    @field:JsonProperty("photo")
    var photo = 0
    @field:JsonProperty("msgbox")
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
