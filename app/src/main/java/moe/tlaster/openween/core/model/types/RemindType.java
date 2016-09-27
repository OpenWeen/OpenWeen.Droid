package moe.tlaster.openween.core.model.types;

/**
 * Created by Tlaster on 2016/9/7.
 */
public enum RemindType {

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

    private final String text;
    RemindType(final String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }

}
