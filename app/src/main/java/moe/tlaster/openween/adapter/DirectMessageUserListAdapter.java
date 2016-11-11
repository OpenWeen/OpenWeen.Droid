package moe.tlaster.openween.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import moe.tlaster.openween.R;
import moe.tlaster.openween.activity.DetailActivity;
import moe.tlaster.openween.activity.DirectMessageActivity;
import moe.tlaster.openween.common.helpers.WeiboCardHelper;
import moe.tlaster.openween.core.model.directmessage.DirectMessageUserModel;

/**
 * Created by Asahi on 2016/11/7.
 */

public class DirectMessageUserListAdapter extends BaseQuickAdapter<DirectMessageUserModel, BaseViewHolder> {

    public DirectMessageUserListAdapter() {
        super(R.layout.user_header_template, null);
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, DirectMessageUserModel directMessageUserModel) {
        baseViewHolder.setText(R.id.user_name, directMessageUserModel.getUser().getScreenName());
        baseViewHolder.setText(R.id.user_sub_text, directMessageUserModel.getDirectMessage().getText());
        Glide.with(mContext).load(directMessageUserModel.getUser().getAvatarLarge()).into((ImageView) baseViewHolder.getView(R.id.user_img));
        int padding = mContext.getResources().getDimensionPixelSize(R.dimen.dp_10);
        ((TextView) baseViewHolder.getView(R.id.user_sub_text)).setLines(1);
        baseViewHolder.getConvertView().setPadding(padding, padding, padding, padding);
        baseViewHolder.getConvertView().setOnClickListener(view -> {
            Intent i = new Intent(mContext, DirectMessageActivity.class);
            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, baseViewHolder.getConvertView(), mContext.getString(R.string.user_header_name));
            i.putExtra(mContext.getString(R.string.user_item_name), directMessageUserModel.getUser());
            mContext.startActivity(i, transitionActivityOptions.toBundle());
        });
        //baseViewHolder.getConvertView().setOnClickListener(view -> WeiboCardHelper.goUserActivity(userModel.getScreenName(), mContext));
    }
}
