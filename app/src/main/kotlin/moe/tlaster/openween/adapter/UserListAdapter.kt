package moe.tlaster.openween.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import moe.tlaster.openween.R
import moe.tlaster.openween.common.helpers.WeiboCardHelper
import moe.tlaster.openween.core.model.user.UserModel

/**
 * Created by Asahi on 2016/11/3.
 */

class UserListAdapter : BaseQuickAdapter<UserModel, BaseViewHolder>(R.layout.user_header_template, null) {

    override fun convert(baseViewHolder: BaseViewHolder, userModel: UserModel) {
        baseViewHolder.setText(R.id.user_name, userModel.screenName)
        baseViewHolder.setText(R.id.user_sub_text, userModel.description)
        Glide.with(mContext).load(userModel.avatarLarge).into(baseViewHolder.getView<View>(R.id.user_img) as ImageView)
        val padding = mContext.resources.getDimensionPixelSize(R.dimen.dp_10)
        (baseViewHolder.getView<View>(R.id.user_sub_text) as TextView).setLines(1)
        baseViewHolder.getConvertView().setPadding(padding, padding, padding, padding)
        baseViewHolder.getConvertView().setOnClickListener { view -> WeiboCardHelper.goUserActivity(userModel.screenName!!, mContext) }
    }
}
