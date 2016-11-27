package moe.tlaster.openween.common.controls

import android.graphics.Color
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

/**
 * Created by Asahi on 2016/9/23.
 */

open class ExClickableSpan : ClickableSpan() {
    override fun onClick(widget: View) {

    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.color = Color.BLUE
        ds.isUnderlineText = false
    }
}
