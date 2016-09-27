package moe.tlaster.openween.core.model.types;

/**
 * Created by Tlaster on 2016/9/2.
 */
public enum RepostType {
    None(0),
    CommentCurrentWeibo(1),
    CommentOriginWeibo(2),
    CommentAll(3),;

    private final int value;
    public int getValue() {
        return value;
    }
    RepostType(int value) {
        this.value = value;
    }
}
