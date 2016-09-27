package moe.tlaster.openween.core.model.types;

/**
 * Created by Tlaster on 2016/9/2.
 */
public enum SourceType {
    All(0),
    Weibo(1),
    MicroGroup(2);

    private final int value;
    public int getValue() {
        return value;
    }
    SourceType(int value) {
        this.value = value;
    }
}
