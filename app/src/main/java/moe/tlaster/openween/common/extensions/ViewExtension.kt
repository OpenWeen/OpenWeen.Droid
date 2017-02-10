package moe.tlaster.openween.common.extensions

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.view.View

/**
 * Created by Tlaster on 2017/2/8.
 */
object ViewExtension {
    fun View.color(@ColorRes colorId: Int) : Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) resources.getColor(colorId, context.theme) else resources.getColor(colorId)
    }
    fun View.drawable(@DrawableRes drawableId: Int) : Drawable {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) resources.getDrawable(drawableId, context.theme) else resources.getDrawable(drawableId)
    }
    fun View.string(@StringRes stringId: Int) : String {
        return resources.getString(stringId)
    }
}