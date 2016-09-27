package moe.tlaster.openween.core.model.types;

/**
 * Created by Tlaster on 2016/9/2.
 */
public enum FeatureType {
    All(0),
    Origin(1),
    Picture(2),
    Video(3),
    Music(4);

    private final int value;
    public int getValue() {
        return value;
    }
    FeatureType(int value) {
        this.value = value;
    }
}
