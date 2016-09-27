package moe.tlaster.openween.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import moe.tlaster.openween.R;
import moe.tlaster.openween.common.controls.WeiboTextBlock;
import moe.tlaster.openween.core.model.BaseModel;

/**
 * Created by Asahi on 2016/9/23.
 */

public class BaseModelAdapter extends BaseMultiItemQuickAdapter<BaseModel> {

    public BaseModelAdapter() {
        super(null);
        addItemType(BaseModel.MESSAGE, R.layout.message_template);
        addItemType(BaseModel.COMMENT, R.layout.comment_template);
        WeiboTextBlock weiboTextBlock = new WeiboTextBlock(mContext);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BaseModel baseModel) {
        View userHeader = baseViewHolder.getView(R.id.user_header);
        ((TextView) userHeader.findViewById(R.id.user_name)).setText(baseModel.getUser().getScreenName());
        ((TextView) userHeader.findViewById(R.id.created_time)).setText(baseModel.getCreatedAtDiffForHuman());
        Glide.with(mContext).load(baseModel.getUser().getProfileImageUrl()).into((ImageView) userHeader.findViewById(R.id.user_img));
        switch (baseViewHolder.getItemViewType()){
            case BaseModel.MESSAGE:
                break;
            case BaseModel.COMMENT:
                break;
        }

    }
}
