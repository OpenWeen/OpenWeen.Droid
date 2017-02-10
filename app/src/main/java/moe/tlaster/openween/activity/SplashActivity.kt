package moe.tlaster.openween.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mcxiaoke.koi.ext.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity<LoginActivity>()
        finish()
    }
}
