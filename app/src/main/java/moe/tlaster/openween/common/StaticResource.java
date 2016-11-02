package moe.tlaster.openween.common;

import java.util.List;

import moe.tlaster.openween.core.model.EmotionModel;

/**
 * Created by Asahi on 2016/9/24.
 */

public class StaticResource {
    private static long mUid;
    private static List<EmotionModel> mEmotions;

    public static List<EmotionModel> getEmotions() {
        return mEmotions;
    }

    public static void setEmotions(List<EmotionModel> Emotions) {
        StaticResource.mEmotions = Emotions;
    }

    public static long getUid() {
        return mUid;
    }

    public static void setUid(long Uid) {
        StaticResource.mUid = Uid;
    }
}
