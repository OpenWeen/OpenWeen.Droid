package moe.tlaster.openween.view

import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.support.design.widget.AppBarLayout
import android.support.v4.view.PagerAdapter
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import devlight.io.library.ntb.NavigationTabBar
import moe.tlaster.openween.R
import moe.tlaster.openween.activity.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.viewPager
import android.view.ViewGroup
import android.widget.LinearLayout
import com.benny.library.kbinding.view.ViewBinderComponent
import moe.tlaster.openween.common.exViewPager
import moe.tlaster.openween.common.extensions.ViewExtension.color
import moe.tlaster.openween.common.extensions.ViewExtension.drawable
import moe.tlaster.openween.common.navigationTabBar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout


/**
 * Created by Tlaster on 2017/2/8.
 */
class MainPage(var mPosition: Int = Gravity.TOP): ViewBinderComponent<MainActivity> {
    val ID_APPBAR = 1
    override fun builder(): AnkoContext<out MainActivity>.() -> Unit = {
        relativeLayout {
            val pager = exViewPager {
                linearLayout {
                    textView("view1")
                }
                linearLayout {
                    textView("view2")
                }
            }.lparams {
                when (mPosition) {
                    Gravity.BOTTOM -> above(ID_APPBAR)
                    Gravity.TOP -> below(ID_APPBAR)
                    Gravity.START -> rightOf(ID_APPBAR)
                    Gravity.END -> leftOf(ID_APPBAR)
                }
            }
            appBarLayout {
                id = ID_APPBAR
                navigationTabBar {
                    bgColor = color(R.color.colorPrimary)
                    inactiveColor = Color.WHITE
                    setViewPager(pager)
                    models = arrayListOf(
                            NavigationTabBar.Model.Builder(drawable(R.drawable.ic_home_white_24dp), color(R.color.colorAccent)).build(),
                            NavigationTabBar.Model.Builder(drawable(R.drawable.ic_home_white_24dp), color(R.color.colorAccent)).build()
                    )
                }.lparams {
                    when (mPosition) {
                        Gravity.BOTTOM, Gravity.TOP -> height = dip(48)
                        Gravity.START, Gravity.END -> width = dip(48)
                    }
                }
            }.lparams {
                when (mPosition) {
                    Gravity.BOTTOM -> alignParentBottom()
                    Gravity.TOP -> alignParentTop()
                    Gravity.START -> alignParentStart()
                    Gravity.END -> alignParentEnd()
                }
            }
        }
    }

}