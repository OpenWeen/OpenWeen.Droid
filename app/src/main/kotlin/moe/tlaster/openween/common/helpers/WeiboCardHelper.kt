package moe.tlaster.openween.common.helpers

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.preference.PreferenceManager
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView


import com.bumptech.glide.Glide
import com.jaeger.ninegridimageview.NineGridImageView

import java.util.Arrays
import java.util.HashSet

import moe.tlaster.openween.R
import moe.tlaster.openween.activity.PostWeiboActivity
import moe.tlaster.openween.activity.UserActivity
import moe.tlaster.openween.adapter.WeiboImageAdapter
import moe.tlaster.openween.common.controls.WeiboTextBlock
import moe.tlaster.openween.common.entities.PostWeiboType
import moe.tlaster.openween.core.model.BaseModel
import moe.tlaster.openween.core.model.comment.CommentModel
import moe.tlaster.openween.core.model.status.MessageModel

/**
 * Created by Asahi on 2016/10/27.
 */

internal object WeiboCardHelper {
    @JvmOverloads fun setData(baseView: View, baseModel: BaseModel, context: Context, isEnableRepost: Boolean = true, textColor: Int = Color.BLACK) {
        baseView.visibility = View.VISIBLE
        val weiboContentContainer = baseView.findViewById(R.id.weibo_content_container)
        val weiboRepostContainer = baseView.findViewById(R.id.weibo_repost_container)
        val weiboRepostLinear = baseView.findViewById(R.id.weibo_repost_linear)
        val enableImage = !((PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.is_auto_disable_image_key), false) && !DeviceHelper.checkWifiOnAndConnected(context)) || PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.is_disable_image_key), false))
        setWeiboContent(weiboContentContainer, baseModel, context = context, textColor = textColor, enableImage = enableImage)
        if (baseModel is MessageModel) {
            baseView.findViewById(R.id.comment).visibility = View.GONE
            baseView.findViewById(R.id.weibo_action).visibility = View.VISIBLE
            if (baseModel.retweetedStatus != null && isEnableRepost) {
                weiboRepostLinear.visibility = View.VISIBLE
                setWeiboContent(weiboRepostContainer, baseModel.retweetedStatus as MessageModel, context = context, enableImage = enableImage)
            } else {
                weiboRepostLinear.visibility = View.GONE
            }
            val weiboAction = baseView.findViewById(R.id.weibo_action)
            weiboAction.visibility = View.VISIBLE
            (weiboAction.findViewById(R.id.like_count) as TextView).text = baseModel.attitudesCount.toString()
            (weiboAction.findViewById(R.id.comment_count) as TextView).text = baseModel.commentsCount.toString()
            (weiboAction.findViewById(R.id.repost_count) as TextView).text = baseModel.repostsCount.toString()
            weiboAction.findViewById(R.id.repost).setOnClickListener { v ->
                val i = Intent(context, PostWeiboActivity::class.java)
                i.putExtra(context.getString(R.string.post_weibo_type_name), PostWeiboType.RePost)
                i.putExtra(context.getString(R.string.post_weibo_id_name), baseModel.id)
                i.putExtra(context.getString(R.string.post_weibo_data_name), if (baseModel.retweetedStatus == null) "" else "//@" + baseModel.user!!.name + ":" + baseModel.text)
                context.startActivity(i)
            }
            weiboAction.findViewById(R.id.comment).setOnClickListener { v ->
                val i = Intent(context, PostWeiboActivity::class.java)
                i.putExtra(context.getString(R.string.post_weibo_type_name), PostWeiboType.Comment)
                i.putExtra(context.getString(R.string.post_weibo_id_name), baseModel.id)
                context.startActivity(i)
            }
        } else if (baseModel is CommentModel) {
            baseView.findViewById(R.id.comment).visibility = View.VISIBLE
            baseView.findViewById(R.id.weibo_action).visibility = View.GONE
            if (isEnableRepost) {
                weiboRepostLinear.visibility = View.VISIBLE
                setWeiboContent(baseView.findViewById(R.id.weibo_repost_container), baseModel.status as MessageModel, false, context)
            } else {
                weiboRepostLinear.visibility = View.GONE
            }
            baseView.findViewById(R.id.comment).setOnClickListener(getReplyCommentListener(baseModel, context))
        }
    }

    fun shouldBlock(context: Context, baseModel: BaseModel): Boolean {
        val blockText = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.block_text_key), null)
        val blockUserid = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.block_userid_key), null)
        if (TextUtils.isEmpty(blockText) && TextUtils.isEmpty(blockUserid)) return false
        val blockTextList = HashSet(Arrays.asList<String>(*blockText?.split(",".toRegex())?.dropLastWhile(String::isEmpty)?.toTypedArray() ?: arrayOfNulls<String>(0)))
        val blockUserList = HashSet(Arrays.asList<String>(*blockUserid?.split(",".toRegex())?.dropLastWhile(String::isEmpty)?.toTypedArray() ?: arrayOfNulls<String>(0)))
        if (baseModel is MessageModel) {
            return blockUserList.contains(baseModel.user!!.id.toString()) || baseModel.retweetedStatus != null && baseModel.retweetedStatus!!.user != null && blockUserList.contains(baseModel.retweetedStatus!!.user!!.id.toString()) || blockTextList.any { item -> !TextUtils.isEmpty(item) && baseModel.text.contains(item) } || baseModel.retweetedStatus != null && blockTextList.any { item -> !TextUtils.isEmpty(item) && baseModel.retweetedStatus!!.text.contains(item) }
        } else if (baseModel is CommentModel) {
            return blockUserList.contains(baseModel.user?.id.toString()) || baseModel.replyComment != null && baseModel.replyComment!!.user != null && blockUserList.contains(baseModel.replyComment!!.user!!.id.toString()) || blockTextList.any { item -> !TextUtils.isEmpty(item) && baseModel.text.contains(item) } || baseModel.replyComment != null && blockTextList.any { item -> !TextUtils.isEmpty(item) && baseModel.replyComment!!.text.contains(item) }
        }
        return false
    }

    private fun getReplyCommentListener(commentModel: CommentModel, context: Context): View.OnClickListener {
        return View.OnClickListener { v ->
            val i = Intent(context, PostWeiboActivity::class.java)
            i.putExtra(context.getString(R.string.post_weibo_type_name), PostWeiboType.ReplyComment)
            i.putExtra(context.getString(R.string.post_weibo_data_name), "回复@" + commentModel.user!!.name + ":")
            i.putExtra(context.getString(R.string.post_weibo_id_name), commentModel.status!!.id)
            i.putExtra(context.getString(R.string.post_weibo_cid_name), commentModel.id)
            context.startActivity(i)
        }
    }


    private fun setWeiboContent(view: View, item: BaseModel, enableImage: Boolean = true, context: Context, textColor: Int = Color.BLACK) {
        val userHeader = view.findViewById(R.id.user_header)
        val userName = userHeader.findViewById(R.id.user_name) as TextView
        val time = userHeader.findViewById(R.id.user_sub_text) as TextView
        val content = view.findViewById(R.id.weibo_content) as WeiboTextBlock
        userName.setTextColor(textColor)
        content.setTextColor(textColor)
        if (item.user != null) {
            Glide.with(context).load(item.user!!.avatarLarge).into(userHeader.findViewById(R.id.user_img) as ImageView)
            userName.text = item.user!!.screenName
            userName.setOnClickListener { v -> goUserActivity(item.user!!.screenName!!, context) }
            userHeader.setOnClickListener { v -> goUserActivity(item.user!!.screenName!!, context) }
        }
        time.text = item.createdAtDiffForHuman
        content.text = item.text
        content.userClicked = object : WeiboTextBlock.WeiboTextBlockCallback {
            override fun call(value: String) {
                goUserActivity(value, context)
            }
        }
        val nineGridImageView = view.findViewById(R.id.weibo_img) as NineGridImageView<*>
        if (item is MessageModel) {
            if (enableImage) {
                nineGridImageView.visibility = View.VISIBLE
                nineGridImageView.setAdapter(WeiboImageAdapter())
                nineGridImageView.setImagesData(item.picUrls)
            } else {
                nineGridImageView.setVisibility(View.GONE)
            }
        } else if (item is CommentModel) {
            nineGridImageView.visibility = View.GONE
            content.setOnClickListener(getReplyCommentListener(item, context))
        }
    }

    fun goUserActivity(userName: String, context: Context) {
        val intent = Intent(context, UserActivity::class.java)
        intent.putExtra(context.getString(R.string.user_page_username_name), userName)
        context.startActivity(intent)
    }
}
