package moe.tlaster.openween.common;

/**
 * Created by Asahi on 2016/9/24.
 */

public class StaticResource {
    private static long mUid;

    public static long getUid() {
        return mUid;
    }

    public static void setUid(long Uid) {
        StaticResource.mUid = Uid;
    }
}
