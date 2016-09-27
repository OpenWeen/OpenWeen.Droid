package moe.tlaster.openween.core.model.types;

/**
 * Created by Tlaster on 2016/9/2.
 */
public enum UrlType {
    WebLink(0),
    Video(1),
    Music(2),
    Event(3),
    Vote(5);

    UrlType(int i) {
        this.value = i;
    }

    private final int value;
    public int getValue() {
        return value;
    }
}
