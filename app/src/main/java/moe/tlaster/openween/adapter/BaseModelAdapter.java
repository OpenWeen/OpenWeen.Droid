package moe.tlaster.openween.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jaeger.ninegridimageview.NineGridImageView;

import java.util.List;

import moe.tlaster.openween.R;
import moe.tlaster.openween.common.controls.WeiboTextBlock;
import moe.tlaster.openween.core.model.BaseModel;
import moe.tlaster.openween.core.model.comment.CommentModel;
import moe.tlaster.openween.core.model.status.MessageModel;

/**
 * Created by Asahi on 2016/9/23.
 */

public class BaseModelAdapter extends BaseMultiItemQuickAdapter<BaseModel> {

    public BaseModelAdapter() {
        super(null);
        addItemType(BaseModel.MESSAGE, R.layout.message_template);
        addItemType(BaseModel.COMMENT, R.layout.comment_template);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BaseModel baseModel) {
        View userHeader = baseViewHolder.getView(R.id.user_header);
        ((TextView) userHeader.findViewById(R.id.user_name)).setText(baseModel.getUser().getScreenName());
        ((TextView) userHeader.findViewById(R.id.created_time)).setText(baseModel.getCreatedAtDiffForHuman());
        Glide.with(mContext).load(baseModel.getUser().getAvatarLarge()).into((ImageView) userHeader.findViewById(R.id.user_img));
        switch (baseViewHolder.getItemViewType()){
            case BaseModel.MESSAGE:{
                MessageModel messageModel = (MessageModel) baseModel;
                baseViewHolder.setText(R.id.weibo_content, messageModel.getText())
                        .setText(R.id.like_count, String.valueOf(messageModel.getAttitudesCount()))
                        .setText(R.id.comment_count, String.valueOf(messageModel.getCommentsCount()))
                        .setText(R.id.repost_count, String.valueOf(messageModel.getRepostsCount()));
                if (messageModel.getPicUrls() != null && messageModel.getPicUrls().size() > 0){
                    NineGridImageView imageView = baseViewHolder.getView(R.id.weibo_img);
                    imageView.setAdapter(new WeiboImageAdapter());
                    imageView.setImagesData(messageModel.getPicUrls());
                    baseViewHolder.getView(R.id.weibo_img).setVisibility(View.VISIBLE);
                } else {
                    baseViewHolder.getView(R.id.weibo_img).setVisibility(View.GONE);
                }
                if (messageModel.getRetweetedStatus() != null) {
                    baseViewHolder.setText(R.id.repost_user_name, messageModel.getRetweetedStatus().getUser().getScreenName())
                            .setText(R.id.repost_weibo_content, messageModel.getRetweetedStatus().getText());
                    if (messageModel.getRetweetedStatus().getPicUrls() != null && messageModel.getRetweetedStatus().getPicUrls().size() > 0){
                        NineGridImageView imageView = baseViewHolder.getView(R.id.repost_weibo_img);
                        imageView.setAdapter(new WeiboImageAdapter());
                        imageView.setImagesData(messageModel.getRetweetedStatus().getPicUrls());
                        baseViewHolder.getView(R.id.repost_weibo_img).setVisibility(View.VISIBLE);
                    } else {
                        baseViewHolder.getView(R.id.repost_weibo_img).setVisibility(View.GONE);
                    }
                    baseViewHolder.getView(R.id.repost_container).setVisibility(View.VISIBLE);
                } else {
                    baseViewHolder.getView(R.id.repost_container).setVisibility(View.GONE);
                }
                baseViewHolder.addOnClickListener(R.id.user_header).addOnClickListener(R.id.repost_user_name).addOnLongClickListener(R.id.weibo_content).addOnClickListener(R.id.repost_weibo_content);
            }
                break;
            case BaseModel.COMMENT:{
                CommentModel commentModel = (CommentModel) baseModel;
                baseViewHolder.setText(R.id.weibo_content, commentModel.getText())
                        .setText(R.id.repost_user_name, commentModel.getStatus().getUser().getScreenName())
                        .setText(R.id.repost_weibo_content, commentModel.getStatus().getText());
            }
                break;
        }

    }
}
