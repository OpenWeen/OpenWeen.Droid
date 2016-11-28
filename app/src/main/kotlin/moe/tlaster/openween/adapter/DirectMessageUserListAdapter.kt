package moe.tlaster.openween.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import moe.tlaster.openween.R
import moe.tlaster.openween.activity.DetailActivity
import moe.tlaster.openween.activity.DirectMessageActivity
import moe.tlaster.openween.common.helpers.WeiboCardHelper
import moe.tlaster.openween.core.model.directmessage.DirectMessageUserModel

/**
 * Created by Asahi on 2016/11/7.
 */

class DirectMessageUserListAdapter : BaseQuickAdapter<DirectMessageUserModel, BaseViewHolder>(R.layout.user_header_template, null) {
    override fun convert(baseViewHolder: BaseViewHolder, directMessageUserModel: DirectMessageUserModel) {
        baseViewHolder.setText(R.id.user_name, directMessageUserModel.user!!.screenName)
        baseViewHolder.setText(R.id.user_sub_text, directMessageUserModel.directMessage!!.text)
        Glide.with(mContext).load(directMessageUserModel.user!!.avatarLarge).into(baseViewHolder.getView<View>(R.id.user_img) as ImageView)
        val padding = mContext.resources.getDimensionPixelSize(R.dimen.dp_10)
        (baseViewHolder.getView<View>(R.id.user_sub_text) as TextView).setLines(1)
        baseViewHolder.getConvertView().setPadding(padding, padding, padding, padding)
        baseViewHolder.getConvertView().setOnClickListener { view ->
            val i = Intent(mContext, DirectMessageActivity::class.java)
            val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(mContext as Activity, baseViewHolder.getConvertView(), mContext.getString(R.string.user_header_name))
            i.putExtra(mContext.getString(R.string.user_item_name), directMessageUserModel.user)
            mContext.startActivity(i, transitionActivityOptions.toBundle())
        }
        //baseViewHolder.getConvertView().setOnClickListener(view -> WeiboCardHelper.goUserActivity(userModel.getScreenName(), mContext));
    }
}
