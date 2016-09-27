package moe.tlaster.openween.core.model.types;

/**
 * Created by Tlaster on 2016/9/2.
 */
public enum ShieldType {
    CurrentOnly(0),
    WithFollowUp(1),;

    private final int value;
    public int getValue() {
        return value;
    }
    ShieldType(int value) {
        this.value = value;
    }
}
