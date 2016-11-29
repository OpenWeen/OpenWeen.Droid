package moe.tlaster.openween.common.controls

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.util.AttributeSet
import android.view.View
import android.widget.TextView


import java.io.File
import java.util.ArrayList
import java.util.NoSuchElementException
import java.util.Objects
import java.util.concurrent.Callable
import java.util.regex.Matcher
import java.util.regex.Pattern

import moe.tlaster.openween.common.StaticResource
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.api.shortUrl.ShortUrl
import moe.tlaster.openween.core.model.status.PictureModel
import moe.tlaster.openween.core.model.url.UrlInfoListModel
import moe.tlaster.openween.core.model.url.UrlInfoModel
import okhttp3.Call
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by Asahi on 2016/9/24.
 */

class WeiboTextBlock : TextView {

    private var mOnClickListener: View.OnClickListener? = null
    private var mCanClick = true

    interface WeiboTextBlockCallback {
        fun call(value: String)
    }
    var userClicked: WeiboTextBlockCallback? = null
    var linkClicked: WeiboTextBlockCallback? = null
    var topicClicked: WeiboTextBlockCallback? = null
    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    override fun setOnClickListener(l: View.OnClickListener) {
        mOnClickListener = View.OnClickListener { view ->
            if (mCanClick)
                l.onClick(view)
            else
                mCanClick = true
        }
        super.setOnClickListener(mOnClickListener)
    }

    override fun setText(text: CharSequence, type: TextView.BufferType) {
        val spannableString: SpannableString
        val index = text.toString().indexOf("\u5168\u6587\uff1a http://m.weibo.cn/")
        if (index != -1) {
            val length = index + 2
            spannableString = SpannableString(text.toString().substring(0, length))
            val span = ForegroundColorSpan(Color.BLUE)
            spannableString.setSpan(span, index, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        } else {
            spannableString = SpannableString(text)
        }
        val matcher = pattern.matcher(spannableString)
        if (matcher.find()) {
            movementMethod = LinkMovementMethod.getInstance()
            matcher.reset()
        }

        while (matcher.find()) {
            val at = matcher.group(1)
            val topic = matcher.group(2)
            val emoji = matcher.group(3)
            val url = matcher.group(4)
            if (at != null) {
                val start = matcher.start(1)
                val end = start + at.length
                val clickableSpan = object : ExClickableSpan() {
                    override fun onClick(widget: View) {
                        userClicked?.call(at.substring(1))
                    }
                }
                spannableString.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            if (topic != null) {
                val start = matcher.start(2)
                val end = start + topic.length
                val clickableSpan = object : ExClickableSpan() {
                    override fun onClick(widget: View) {
                        topicClicked?.call(topic)
                    }
                }
                spannableString.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            if (emoji != null) {
                val start = matcher.start(3)
                val end = start + emoji.length
                try {
                    val filePath = StaticResource.emotions?.filter { it.value == emoji }?.firstOrNull()?.url
                    var bitmap: Bitmap? = BitmapFactory.decodeFile(filePath)
                    if (bitmap != null) {
                        val size = textSize.toInt()
                        bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true)
                        val imageSpan = ImageSpan(context, bitmap, ImageSpan.ALIGN_BASELINE)
                        spannableString.setSpan(imageSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    }
                } catch (e: Exception) {
                }

            }

            if (url != null) {
                val start = matcher.start(4)
                val end = start + url.length
                val clickableSpan = object : ExClickableSpan() {
                    override fun onClick(widget: View) {
                        mCanClick = false
                        linkClicked?.call(url)
                        ShortUrl.info(object : JsonCallback<UrlInfoListModel>() {
                            override fun onError(call: Call, e: Exception, id: Int) {
                                openLink(url)
                            }

                            override fun onResponse(response: UrlInfoListModel, id: Int) {
                                val item = response.urls?.firstOrNull() ?: return
                                when (item.type) {
                                    39 -> {
                                        val picid = item.annotations?.firstOrNull()?.item?.picIds?.firstOrNull()
                                        if (!TextUtils.isEmpty(picid)) {
                                            val intent = Intent(context, WeiboImageList::class.java)
                                            intent.putStringArrayListExtra(WeiboImageList.INTENTNAME, object : ArrayList<String>() {
                                                init {
                                                    add("http://ww1.sinaimg.cn/large/$picid.jpg")
                                                }
                                            })
                                            context.startActivity(intent)
                                        } else {
                                            openLink(item.urlLong!!)
                                        }
                                    }
                                    else -> openLink(item.urlLong!!)
                                }
                            }
                        }, url)
                    }
                }
                spannableString.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
        super.setText(spannableString, type)
    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }

    companion object {

        private val AT = "@[^,\uff0c\uff1a:\\s@]+"
        private val TOPIC = "#[^#]+#"
        private val EMOJI = "\\[[\\w]+\\]"
        private val URL = "http://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"
        private val REGEX = "($AT)|($TOPIC)|($EMOJI)|($URL)"
        private val pattern = Pattern.compile(REGEX)
    }
}
