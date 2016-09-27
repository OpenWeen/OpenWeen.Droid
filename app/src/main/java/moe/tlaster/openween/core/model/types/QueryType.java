package moe.tlaster.openween.core.model.types;

/**
 * Created by Tlaster on 2016/9/2.
 */
public enum QueryType {
    Weibo(1),
    Comment(2),
    DirectMessage(3),;

    private final int value;
    public int getValue() {
        return value;
    }
    QueryType(int value) {
        this.value = value;
    }
}
