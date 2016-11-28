package moe.tlaster.openween.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.support.v4.util.Pair
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jaeger.ninegridimageview.NineGridImageView

import java.util.ArrayList

import de.hdodenhof.circleimageview.CircleImageView
import moe.tlaster.openween.R
import moe.tlaster.openween.activity.DetailActivity
import moe.tlaster.openween.activity.MainActivity
import moe.tlaster.openween.activity.PostWeiboActivity
import moe.tlaster.openween.activity.SplashActivity
import moe.tlaster.openween.activity.UserActivity
import moe.tlaster.openween.common.controls.WeiboTextBlock
import moe.tlaster.openween.common.entities.PostWeiboType
import moe.tlaster.openween.common.helpers.WeiboCardHelper
import moe.tlaster.openween.core.model.BaseModel
import moe.tlaster.openween.core.model.comment.CommentModel
import moe.tlaster.openween.core.model.status.MessageModel
import moe.tlaster.openween.core.model.user.UserModel

/**
 * Created by Asahi on 2016/9/23.
 */

class BaseModelAdapter<T : BaseModel> @JvmOverloads constructor(private val mIsEnableRepost: Boolean = true) : BaseQuickAdapter<T, BaseViewHolder>(R.layout.base_model_template, null) {

    override fun convert(baseViewHolder: BaseViewHolder, p1: T) {
        if (WeiboCardHelper.shouldBlock(mContext, p1)) {
            baseViewHolder.setVisible(R.id.base_model_container, false)
            return
        }
        val baseView = baseViewHolder.getConvertView()
        WeiboCardHelper.setData(baseView, p1, mContext, mIsEnableRepost)
        if (p1 is MessageModel) {
            baseView.findViewById(R.id.weibo_content_container).findViewById(R.id.weibo_content).setOnClickListener { view -> goDetail(p1, baseView) }
            if (p1.retweetedStatus != null)
                baseView.findViewById(R.id.weibo_repost_container).findViewById(R.id.weibo_content).setOnClickListener { view -> goDetail(p1.retweetedStatus as MessageModel, baseView.findViewById(R.id.weibo_repost_container)) }
        }
        if (p1 is CommentModel && p1.status != null)
            baseView.findViewById(R.id.weibo_repost_container).findViewById(R.id.weibo_content).setOnClickListener { view -> goDetail(p1.status as MessageModel, baseView.findViewById(R.id.weibo_repost_container)) }
    }

    private fun goDetail(p1: MessageModel, baseView: View) {
        val i = Intent(mContext, DetailActivity::class.java)
        val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(mContext as Activity, baseView, mContext.getString(R.string.base_model_template_name))
        i.putExtra(mContext.getString(R.string.detail_message_model_name), p1)
        mContext.startActivity(i, transitionActivityOptions.toBundle())
    }
}
