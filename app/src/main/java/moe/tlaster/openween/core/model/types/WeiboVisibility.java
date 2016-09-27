package moe.tlaster.openween.core.model.types;

/**
 * Created by Tlaster on 2016/9/2.
 */
public enum WeiboVisibility {
    All(0),
    OnlyMe(1),
    OnlyFriends(2),
    SpecifiedGroup(3);

    private final int value;
    public int getValue() {
        return value;
    }
    WeiboVisibility(int value) {
        this.value = value;
    }
}
