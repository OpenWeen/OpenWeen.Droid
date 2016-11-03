package moe.tlaster.openween.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import moe.tlaster.openween.R;
import moe.tlaster.openween.common.helpers.WeiboCardHelper;
import moe.tlaster.openween.core.model.user.UserModel;

/**
 * Created by Asahi on 2016/11/3.
 */

public class UserListAdapter extends BaseQuickAdapter<UserModel> {
    public UserListAdapter() {
        super(R.layout.user_header_template, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, UserModel userModel) {
        baseViewHolder.setText(R.id.user_name, userModel.getScreenName());
        baseViewHolder.setText(R.id.user_sub_text, userModel.getDescription());
        Glide.with(mContext).load(userModel.getAvatarLarge()).into((ImageView) baseViewHolder.getView(R.id.user_img));
        int padding = mContext.getResources().getDimensionPixelSize(R.dimen.dp_10);
        ((TextView) baseViewHolder.getView(R.id.user_sub_text)).setLines(1);
        baseViewHolder.getConvertView().setPadding(padding, padding, padding, padding);
        baseViewHolder.getConvertView().setOnClickListener(view -> WeiboCardHelper.goUserActivity(userModel.getScreenName(), mContext));
    }
}
