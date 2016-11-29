package moe.tlaster.openween.activity

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import moe.tlaster.openween.R
import net.hockeyapp.android.FeedbackManager

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }
    fun goMainSite(view: View) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://OpenWeen.github.io"))
        startActivity(browserIntent)
    }
    fun goProjectSite(view: View) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/OpenWeen/OpenWeen.Droid"))
        startActivity(browserIntent)
    }
    fun sendFeedback(view: View) {
        FeedbackManager.showFeedbackActivity(this)
    }
}
