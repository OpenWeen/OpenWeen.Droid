package moe.tlaster.openween.viewmodel

import android.app.Activity
import android.app.Dialog
import android.content.ClipData
import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import com.benny.library.kbinding.annotation.Property
import com.benny.library.kbinding.viewmodel.ViewModel
import moe.tlaster.openween.common.helpers.SettingHelper
import kotlin.properties.Delegates
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.util.Base64
import com.benny.library.kbinding.annotation.Command
import moe.tlaster.openween.R
import moe.tlaster.openween.common.helpers.Setttings
import org.jetbrains.anko.*

/**
 * Created by Tlaster on 2017/2/9.
 */
class LoginViewModel(val activity: Activity) : ViewModel() {
    private val SEPERATOR = "::"
    private val START = "SS"
    private val END = "EE"
    @delegate:Property
    var appId: String by Delegates.property("")
    @delegate:Property
    var appSecret: String by Delegates.property("")
    @delegate:Property
    var redirectUri: String by Delegates.property("")
    @delegate:Property
    var scope: String by Delegates.property("")
    @delegate:Property
    var packageName: String by Delegates.property("")

    @Command
    fun onTextChanged(new: String) {
        if (isLoginData(new)) {
            val data = decodeLoginData(new)
            if (data == null || data.size < 5) return
            appId = data[0].trim { it <= ' ' }
            appSecret = data[1].trim { it <= ' ' }
            redirectUri = data[2].trim { it <= ' ' }
            scope = data[3].trim { it <= ' ' }
            packageName = data[4].trim { it <= ' ' }
        }
    }
    @Command
    fun what() {
        val clipboard = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("data", "SSMjExMTYwNjc5OjoxZTZlMzNkYjA4ZjkxOTIzMDZjNGFmYTBhNjFhZDU2Yzo6aHR0cDovL29hdXRoLndlaWNvLmNjOjplbWFpbCxkaXJlY3RfbWVzc2FnZXNfcmVhZCxkaXJlY3RfbWVzc2FnZXNfd3JpdGUsZnJpZW5kc2hpcHNfZ3JvdXBzX3JlYWQsZnJpZW5kc2hpcHNfZ3JvdXBzX3dyaXRlLHN0YXR1c2VzX3RvX21lX3JlYWQsZm9sbG93X2FwcF9vZmZpY2lhbF9taWNyb2Jsb2csaW52aXRhdGlvbl93cml0ZTo6Y29tLmVpY28ud2VpY286OkVFEE")
        clipboard.primaryClip = clip
        activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://gist.github.com/PeterCxy/3085799055f63c63c911")))
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

    @Command
    fun login() {
        activity.alert {
            customView {
                webView {
                    settings.javaScriptEnabled = true
                    clearHistory()
                    clearCache(true)
                    loadUrl("https://api.weibo.com/oauth2/authorize?client_id=" + appId + "&response_type=token&display=mobile&redirect_uri=" +
                            redirectUri + "&key_hash=" + appSecret + "&packagename=" + packageName + "&scope=" + scope)
                    setWebViewClient(object : WebViewClient() {
                        override fun onPageFinished(view: WebView, url: String) {
                            super.onPageFinished(view, url)
                            if (!url.contains("error") && url.contains("access_token=")) {
                                val tokenIndex = url.indexOf("access_token=")
                                val expiresIndex = url.indexOf("expires_in=")
                                val token = url.substring(tokenIndex + 13, url.indexOf("&", tokenIndex))
                                val expiresIn = url.substring(expiresIndex + 11, url.indexOf("&", expiresIndex))
                                Setttings.AccessToken = listOf(token, Setttings.AccessToken).map(Any::toString)
                                dialog?.dismiss()
                            }
                        }
                    })
                }
            }
            title("登陆")
            cancellable(false)
        }.show()
    }

}