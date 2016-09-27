package moe.tlaster.openween.core.model.types;

/**
 * Created by Tlaster on 2016/9/2.
 */
public enum DirectMessageType {

    Outbox(0),
    Inbox(1),;

    private final int value;
    public int getValue() {
        return value;
    }
    DirectMessageType(int value) {
        this.value = value;
    }
}
