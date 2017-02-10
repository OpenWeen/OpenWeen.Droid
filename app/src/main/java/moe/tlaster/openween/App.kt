package moe.tlaster.openween

import android.app.Application
import android.content.Context

/**
 * Created by Tlaster on 2017/2/7.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppContext = applicationContext
    }
    companion object {
        lateinit var AppContext: Context
    }
}