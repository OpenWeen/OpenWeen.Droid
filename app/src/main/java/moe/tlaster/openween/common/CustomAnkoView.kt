package moe.tlaster.openween.common

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import devlight.io.library.ntb.NavigationTabBar
import moe.tlaster.openween.common.controls.ExViewPager
import org.jetbrains.anko.custom.ankoView
import kotlin.reflect.KCallable

/**
 * Created by Tlaster on 2017/2/8.
 */

inline fun ViewManager.navigationTabBar(theme: Int = 0) = navigationTabBar(theme) { }
inline fun ViewManager.navigationTabBar(theme: Int = 0, init: NavigationTabBar.() -> Unit) = ankoView(::NavigationTabBar, theme, init)
inline fun ViewManager.exViewPager(theme: Int = 0) = exViewPager(theme) { }
inline fun ViewManager.exViewPager(theme: Int = 0, init: ExViewPager.() -> Unit) = ankoView(::ExViewPager, theme, {
    init()
    adapter = object: PagerAdapter() {
        override fun instantiateItem(collection: ViewGroup, position: Int): Any {
            return collection.getChildAt(position)
        }
        override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
            return view === `object` as View
        }
        override fun getCount(): Int {
            return childCount
        }
    }
})

inline fun nameof(any: KCallable<Any>) : String {
    return any.name
}