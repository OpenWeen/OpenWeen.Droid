package moe.tlaster.openween.core.model.types;

/**
 * Created by Tlaster on 2016/9/2.
 */
public enum AuthorType {

    All(0),
    FollowedOnly(1),
    Stranger(2);

    private final int value;
    public int getValue() {
        return value;
    }
    AuthorType(int value) {
        this.value = value;
    }
}
