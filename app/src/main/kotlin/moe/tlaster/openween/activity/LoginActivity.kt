package moe.tlaster.openween.activity

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import butterknife.*

import moe.tlaster.openween.R
import moe.tlaster.openween.common.bindView
import moe.tlaster.openween.common.helpers.SettingHelper

/**
 * Created by Asahi on 2016/9/26.
 */

class LoginActivity : BaseActivity() {
    val mAppId: EditText by bindView(R.id.login_app_id)
    val mAppSecret: EditText by bindView(R.id.login_app_secret)
    val mRedirectUri: EditText by bindView(R.id.login_redirect_uri)
    val mScope: EditText by bindView(R.id.login_scope)
    val mPackageName: EditText by bindView(R.id.login_package_name)

    internal var mExitItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //ButterKnife.bind(this)
        findViewById(R.id.login_next).setOnClickListener { login() }
        findViewById(R.id.login_what).setOnClickListener { what() }
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                val str = s.toString().trim { it <= ' ' }
                if (isLoginData(str)) {
                    val data = decodeLoginData(s.toString())
                    if (data == null || data.size < 5) return
                    mAppId.setText(data[0].trim { it <= ' ' })
                    mAppSecret.setText(data[1].trim { it <= ' ' })
                    mRedirectUri.setText(data[2].trim { it <= ' ' })
                    mScope.setText(data[3].trim { it <= ' ' })
                    mPackageName.setText(data[4].trim { it <= ' ' })
                }
            }
        }
        mAppId.addTextChangedListener(watcher)
        mAppSecret.addTextChangedListener(watcher)
        mRedirectUri.addTextChangedListener(watcher)
        mScope.addTextChangedListener(watcher)
        mPackageName.addTextChangedListener(watcher)
    }

    private fun decodeLoginData(str: String): Array<String>? {
        if (!isLoginData(str))
            return null
        val data = str.substring(START.length, str.length - END.length - 1)
        try {
            return String(Base64.decode(data, Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING or Base64.NO_CLOSE)).split(SEPERATOR.toRegex()).dropLastWhile(String::isEmpty).toTypedArray()
        } catch (e: Exception) {
            return null
        }

    }

    private fun isLoginData(str: String): Boolean {
        return str.startsWith(START) && str.length > START.length + END.length && str.endsWith(END)
    }


    //@OnClick(R.id.login_next)
    fun login() {
        val dialog = Dialog(this, R.style.AppTheme_NoActionBar)
        dialog.setContentView(R.layout.login_web_view)
        dialog.window!!.statusBarColor = window.statusBarColor
        val webView = dialog.findViewById(R.id.login_webview) as WebView
        webView.settings.javaScriptEnabled = true
        webView.clearHistory()
        webView.clearCache(true)
        webView.loadUrl("https://api.weibo.com/oauth2/authorize?client_id=" + mAppId.text + "&response_type=token&display=mobile&redirect_uri=" +
                mRedirectUri.text + "&key_hash=" + mAppSecret.text + "&packagename=" + mPackageName.text + "&scope=" + mScope.text)
        webView.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                if (!url.contains("error") && url.contains("access_token=")) {
                    val tokenIndex = url.indexOf("access_token=")
                    val expiresIndex = url.indexOf("expires_in=")
                    val token = url.substring(tokenIndex + 13, url.indexOf("&", tokenIndex))
                    val expiresIn = url.substring(expiresIndex + 11, url.indexOf("&", expiresIndex))
                    SettingHelper.setListSetting(this@LoginActivity, SettingHelper.ACCESSTOKEN, false, token)
                    goSplash()
                    dialog.dismiss()
                }
            }
        })
        dialog.show()
        dialog.setTitle("登陆")
        dialog.setCancelable(true)
    }

    //@OnClick(R.id.login_what)
    fun what() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("data", "SSMjExMTYwNjc5OjoxZTZlMzNkYjA4ZjkxOTIzMDZjNGFmYTBhNjFhZDU2Yzo6aHR0cDovL29hdXRoLndlaWNvLmNjOjplbWFpbCxkaXJlY3RfbWVzc2FnZXNfcmVhZCxkaXJlY3RfbWVzc2FnZXNfd3JpdGUsZnJpZW5kc2hpcHNfZ3JvdXBzX3JlYWQsZnJpZW5kc2hpcHNfZ3JvdXBzX3dyaXRlLHN0YXR1c2VzX3RvX21lX3JlYWQsZm9sbG93X2FwcF9vZmZpY2lhbF9taWNyb2Jsb2csaW52aXRhdGlvbl93cml0ZTo6Y29tLmVpY28ud2VpY286OkVFEE")
        clipboard.primaryClip = clip
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://gist.github.com/PeterCxy/3085799055f63c63c911"))
        startActivity(browserIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.clear()

        mExitItem = menu.add("取消")
        MenuItemCompat.setShowAsAction(mExitItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS)

        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (mExitItem == item) {
            cancel()
            return true
        }
        return false
    }


    fun cancel() {
        val homeIntent = Intent(Intent.ACTION_MAIN)
        homeIntent.addCategory(Intent.CATEGORY_HOME)
        homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(homeIntent)
    }

    companion object {

        private val SEPERATOR = "::"
        private val START = "SS"
        private val END = "EE"
    }

}
