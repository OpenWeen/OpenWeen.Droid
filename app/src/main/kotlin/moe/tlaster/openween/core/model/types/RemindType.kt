package moe.tlaster.openween.core.model.types

/**
 * Created by Tlaster on 2016/9/7.
 */
enum class RemindType constructor(private val text: String) {

    Follower("follower"),
    Comment("cmt"),
    DirectMessage("dm"),
    MentionStatus("mention_status"),
    MentionComment("mention_cmt"),
    Group("group"),
    Notice("notice"),
    Invite("invite"),
    Badge("badge"),
    Photo("photo");

    override fun toString(): String {
        return text
    }

}
