package moe.tlaster.openween.common.helpers;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Asahi on 2016/10/12.
 */

public class TimeHelper {
    private static PrettyTime mPrettyTime = new PrettyTime(new Locale("ZH_CN"));
    private static SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);

    public static PrettyTime getmPrettyTime() {
        return mPrettyTime;
    }

    public static SimpleDateFormat getmSimpleDateFormat() {
        return mSimpleDateFormat;
    }
}
