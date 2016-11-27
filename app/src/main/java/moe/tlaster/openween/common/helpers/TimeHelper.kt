package moe.tlaster.openween.common.helpers

import org.ocpsoft.prettytime.PrettyTime

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by Asahi on 2016/10/12.
 */

object TimeHelper {
    val prettyTime = PrettyTime(Locale("ZH_CN"))
    val simpleDateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US)
}
