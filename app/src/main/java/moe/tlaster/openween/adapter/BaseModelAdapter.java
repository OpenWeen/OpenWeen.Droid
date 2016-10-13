package moe.tlaster.openween.adapter;

import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jaeger.ninegridimageview.NineGridImageView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import moe.tlaster.openween.R;
import moe.tlaster.openween.activity.MainActivity;
import moe.tlaster.openween.activity.PostWeiboActivity;
import moe.tlaster.openween.activity.UserActivity;
import moe.tlaster.openween.common.controls.WeiboTextBlock;
import moe.tlaster.openween.common.entities.PostWeiboType;
import moe.tlaster.openween.core.model.BaseModel;
import moe.tlaster.openween.core.model.comment.CommentModel;
import moe.tlaster.openween.core.model.status.MessageModel;
import moe.tlaster.openween.core.model.user.UserModel;

/**
 * Created by Asahi on 2016/9/23.
 */

public class BaseModelAdapter<T extends BaseModel> extends BaseQuickAdapter<BaseModel> {

    public BaseModelAdapter() {
        super(R.layout.base_model_template, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BaseModel baseModel) {
        View weiboContentContainer = baseViewHolder.getView(R.id.weibo_content_container);
        View weiboRepostContainer = baseViewHolder.getView(R.id.weibo_repost_container);
        View weiboRepostLinear = baseViewHolder.getView(R.id.weibo_repost_linear);
        setWeiboContent(weiboContentContainer, baseModel);
        if (baseModel instanceof MessageModel) {
            baseViewHolder.getView(R.id.comment).setVisibility(View.GONE);
            baseViewHolder.getView(R.id.weibo_action).setVisibility(View.VISIBLE);
            MessageModel messageModel = (MessageModel) baseModel;
            if (messageModel.getRetweetedStatus() != null) {
                weiboRepostLinear.setVisibility(View.VISIBLE);
                setWeiboContent(weiboRepostContainer, messageModel.getRetweetedStatus());
            } else {
                weiboRepostLinear.setVisibility(View.GONE);
            }
            View weiboAction = baseViewHolder.getView(R.id.weibo_action);
            ((TextView) weiboAction.findViewById(R.id.like_count)).setText(String.valueOf(messageModel.getAttitudesCount()));
            ((TextView) weiboAction.findViewById(R.id.comment_count)).setText(String.valueOf(messageModel.getCommentsCount()));
            ((TextView) weiboAction.findViewById(R.id.repost_count)).setText(String.valueOf(messageModel.getRepostsCount()));
            weiboAction.findViewById(R.id.repost).setOnClickListener(v -> {
                Intent i = new Intent(mContext, PostWeiboActivity.class);
                i.putExtra(mContext.getString(R.string.post_weibo_type_name), PostWeiboType.RePost);
                i.putExtra(mContext.getString(R.string.post_weibo_id_name), messageModel.getID());
                i.putExtra(mContext.getString(R.string.post_weibo_data_name), messageModel.getRetweetedStatus() == null ? "" : "//@" + messageModel.getUser().getName() + ":" + messageModel.getText());
                mContext.startActivity(i);
            });
            weiboAction.findViewById(R.id.comment).setOnClickListener(v -> {
                Intent i = new Intent(mContext, PostWeiboActivity.class);
                i.putExtra(mContext.getString(R.string.post_weibo_type_name), PostWeiboType.Comment);
                i.putExtra(mContext.getString(R.string.post_weibo_id_name), messageModel.getID());
                mContext.startActivity(i);
            });
        } else if (baseModel instanceof CommentModel) {
            baseViewHolder.getView(R.id.comment).setVisibility(View.VISIBLE);
            baseViewHolder.getView(R.id.weibo_action).setVisibility(View.GONE);
            CommentModel commentModel = (CommentModel) baseModel;
            weiboRepostLinear.setVisibility(View.VISIBLE);
            setWeiboContent(baseViewHolder.getView(R.id.weibo_repost_container), commentModel.getStatus(), false);
            baseViewHolder.getView(R.id.comment).setOnClickListener(getReplyCommentListener(commentModel));
        }
    }

    private View.OnClickListener getReplyCommentListener(CommentModel commentModel){
        return v -> {
            Intent i = new Intent(mContext, PostWeiboActivity.class);
            i.putExtra(mContext.getString(R.string.post_weibo_type_name), PostWeiboType.ReplyComment);
            i.putExtra(mContext.getString(R.string.post_weibo_data_name), "回复@" + commentModel.getUser().getName() + ":");
            i.putExtra(mContext.getString(R.string.post_weibo_id_name), commentModel.getStatus().getID());
            i.putExtra(mContext.getString(R.string.post_weibo_cid_name), commentModel.getID());
            mContext.startActivity(i);
        };
    }


    private void setWeiboContent(View view, BaseModel item) {
        setWeiboContent(view, item, true);
    }


    private void setWeiboContent(View view, BaseModel item, boolean enableImage) {
        TextView userName = (TextView) view.findViewById(R.id.user_name);
        TextView time = (TextView) view.findViewById(R.id.created_time);
        WeiboTextBlock content = (WeiboTextBlock) view.findViewById(R.id.weibo_content);
        if (item.getUser() != null) {
            Glide.with(mContext).load(item.getUser().getAvatarLarge()).into((ImageView) view.findViewById(R.id.user_img));
            userName.setText(item.getUser().getScreenName());
            userName.setOnClickListener(v -> goUserActivity(item.getUser().getName()));
            view.findViewById(R.id.user_img).setOnClickListener(v -> goUserActivity(item.getUser().getName()));
        }
        time.setText(item.getCreatedAtDiffForHuman());
        content.setText(item.getText());
        content.setUserClicked(this::goUserActivity);
        NineGridImageView nineGridImageView = (NineGridImageView) view.findViewById(R.id.weibo_img);
        if (item instanceof MessageModel) {
            if (enableImage) {
                nineGridImageView.setVisibility(View.VISIBLE);
                MessageModel model = (MessageModel) item;
                nineGridImageView.setAdapter(new WeiboImageAdapter());
                nineGridImageView.setImagesData(model.getPicUrls());
            } else {
                nineGridImageView.setVisibility(View.GONE);
            }
        } else if (item instanceof CommentModel) {
            CommentModel commentModel = (CommentModel) item;
            nineGridImageView.setVisibility(View.GONE);
            content.setOnClickListener(getReplyCommentListener(commentModel));
        }
    }
    private void goUserActivity(String userName){
        Intent intent = new Intent(mContext, UserActivity.class);
        intent.putExtra(mContext.getString(R.string.user_page_username_name), userName);
        mContext.startActivity(intent);
    }
}
