package moe.tlaster.openween.common.helpers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.bumptech.glide.Glide;
import com.jaeger.ninegridimageview.NineGridImageView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import moe.tlaster.openween.R;
import moe.tlaster.openween.activity.PostWeiboActivity;
import moe.tlaster.openween.activity.UserActivity;
import moe.tlaster.openween.adapter.WeiboImageAdapter;
import moe.tlaster.openween.common.controls.WeiboTextBlock;
import moe.tlaster.openween.common.entities.PostWeiboType;
import moe.tlaster.openween.core.model.BaseModel;
import moe.tlaster.openween.core.model.comment.CommentModel;
import moe.tlaster.openween.core.model.status.MessageModel;

/**
 * Created by Asahi on 2016/10/27.
 */

public class WeiboCardHelper {
    public static void setData(View baseView, BaseModel baseModel, Context context) {
        setData(baseView, baseModel, context, true);
    }
    public static void setData(View baseView, BaseModel baseModel, Context context, boolean isEnableRepost) {
        setData(baseView, baseModel, context, isEnableRepost, Color.BLACK);
    }
    public static void setData(View baseView, BaseModel baseModel, Context context, boolean isEnableRepost, int textColor) {
        baseView.setVisibility(View.VISIBLE);
        View weiboContentContainer = baseView.findViewById(R.id.weibo_content_container);
        View weiboRepostContainer = baseView.findViewById(R.id.weibo_repost_container);
        View weiboRepostLinear = baseView.findViewById(R.id.weibo_repost_linear);
        setWeiboContent(weiboContentContainer, baseModel, context, textColor);
        if (baseModel instanceof MessageModel) {
            baseView.findViewById(R.id.comment).setVisibility(View.GONE);
            baseView.findViewById(R.id.weibo_action).setVisibility(View.VISIBLE);
            MessageModel messageModel = (MessageModel) baseModel;
            if (messageModel.getRetweetedStatus() != null && isEnableRepost) {
                weiboRepostLinear.setVisibility(View.VISIBLE);
                setWeiboContent(weiboRepostContainer, messageModel.getRetweetedStatus(), context);
            } else {
                weiboRepostLinear.setVisibility(View.GONE);
            }
            View weiboAction = baseView.findViewById(R.id.weibo_action);
            weiboAction.setVisibility(View.VISIBLE);
            ((TextView) weiboAction.findViewById(R.id.like_count)).setText(String.valueOf(messageModel.getAttitudesCount()));
            ((TextView) weiboAction.findViewById(R.id.comment_count)).setText(String.valueOf(messageModel.getCommentsCount()));
            ((TextView) weiboAction.findViewById(R.id.repost_count)).setText(String.valueOf(messageModel.getRepostsCount()));
            weiboAction.findViewById(R.id.repost).setOnClickListener(v -> {
                Intent i = new Intent(context, PostWeiboActivity.class);
                i.putExtra(context.getString(R.string.post_weibo_type_name), PostWeiboType.RePost);
                i.putExtra(context.getString(R.string.post_weibo_id_name), messageModel.getID());
                i.putExtra(context.getString(R.string.post_weibo_data_name), messageModel.getRetweetedStatus() == null ? "" : "//@" + messageModel.getUser().getName() + ":" + messageModel.getText());
                context.startActivity(i);
            });
            weiboAction.findViewById(R.id.comment).setOnClickListener(v -> {
                Intent i = new Intent(context, PostWeiboActivity.class);
                i.putExtra(context.getString(R.string.post_weibo_type_name), PostWeiboType.Comment);
                i.putExtra(context.getString(R.string.post_weibo_id_name), messageModel.getID());
                context.startActivity(i);
            });
        } else if (baseModel instanceof CommentModel) {
            baseView.findViewById(R.id.comment).setVisibility(View.VISIBLE);
            baseView.findViewById(R.id.weibo_action).setVisibility(View.GONE);
            CommentModel commentModel = (CommentModel) baseModel;
            if (isEnableRepost) {
                weiboRepostLinear.setVisibility(View.VISIBLE);
                setWeiboContent(baseView.findViewById(R.id.weibo_repost_container), commentModel.getStatus(), false, context);
            } else {
                weiboRepostLinear.setVisibility(View.GONE);
            }
            baseView.findViewById(R.id.comment).setOnClickListener(getReplyCommentListener(commentModel, context));
        }
    }

    public static boolean shouldBlock(Context context, BaseModel baseModel) {
        String blockText = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.block_text_key), null);
        String blockUserid = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.block_userid_key), null);
        if (TextUtils.isEmpty(blockText) && TextUtils.isEmpty(blockUserid)) return false;
        Set<String> blockTextList = new HashSet<>(Arrays.asList(blockText != null ? blockText.split(",") : new String[0]));
        Set<String> blockUserList = new HashSet<>(Arrays.asList(blockUserid != null ? blockUserid.split(",") : new String[0]));
        if (baseModel instanceof MessageModel) {
            MessageModel model = (MessageModel) baseModel;
            return blockUserList.contains(String.valueOf(model.getUser().getID())) ||
                    (model.getRetweetedStatus() != null && model.getRetweetedStatus().getUser() != null && blockUserList.contains(String.valueOf(model.getRetweetedStatus().getUser().getID()))) ||
                    Stream.of(blockTextList).anyMatch(item -> !TextUtils.isEmpty(item) && model.getText().contains(item)) ||
                    (model.getRetweetedStatus() != null && model.getRetweetedStatus().getText() != null && Stream.of(blockTextList).anyMatch(item -> !TextUtils.isEmpty(item) && model.getRetweetedStatus().getText().contains(item)));
        } else if (baseModel instanceof CommentModel) {
            CommentModel model = (CommentModel) baseModel;
            return blockUserList.contains(String.valueOf(model.getUser().getID())) ||
                    (model.getReplyComment() != null && model.getReplyComment().getUser() != null && blockUserList.contains(String.valueOf(model.getReplyComment().getUser().getID()))) ||
                    Stream.of(blockTextList).anyMatch(item -> !TextUtils.isEmpty(item) && model.getText().contains(item)) ||
                    (model.getReplyComment() != null && model.getReplyComment().getText() != null && Stream.of(blockTextList).anyMatch(item -> !TextUtils.isEmpty(item) && model.getReplyComment().getText().contains(item)));
        }
        return false;
    }

    private static View.OnClickListener getReplyCommentListener(CommentModel commentModel, Context context){
        return v -> {
            Intent i = new Intent(context, PostWeiboActivity.class);
            i.putExtra(context.getString(R.string.post_weibo_type_name), PostWeiboType.ReplyComment);
            i.putExtra(context.getString(R.string.post_weibo_data_name), "回复@" + commentModel.getUser().getName() + ":");
            i.putExtra(context.getString(R.string.post_weibo_id_name), commentModel.getStatus().getID());
            i.putExtra(context.getString(R.string.post_weibo_cid_name), commentModel.getID());
            context.startActivity(i);
        };
    }


    private static void setWeiboContent(View view, BaseModel item, Context context) {
        setWeiboContent(view, item, true, context);
    }
    private static void setWeiboContent(View view, BaseModel item, Context context, int textColor) {
        setWeiboContent(view, item, true, context, textColor);
    }
    private static void setWeiboContent(View view, BaseModel item, boolean enableImage, Context context) {
        setWeiboContent(view, item, enableImage, context, Color.BLACK);
    }
    private static void setWeiboContent(View view, BaseModel item, boolean enableImage, Context context, int textColor) {
        View userHeader = view.findViewById(R.id.user_header);
        TextView userName = (TextView) userHeader.findViewById(R.id.user_name);
        TextView time = (TextView) userHeader.findViewById(R.id.user_sub_text);
        WeiboTextBlock content = (WeiboTextBlock) view.findViewById(R.id.weibo_content);
        userName.setTextColor(textColor);
        content.setTextColor(textColor);
        if (item.getUser() != null) {
            Glide.with(context).load(item.getUser().getAvatarLarge()).into((ImageView) userHeader.findViewById(R.id.user_img));
            userName.setText(item.getUser().getScreenName());
            userName.setOnClickListener(v -> goUserActivity(item.getUser().getScreenName(), context));
            userHeader.setOnClickListener(v -> goUserActivity(item.getUser().getScreenName(), context));
        }
        time.setText(item.getCreatedAtDiffForHuman());
        content.setText(item.getText());
        content.setUserClicked(data -> goUserActivity(data, context));
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
            content.setOnClickListener(getReplyCommentListener(commentModel, context));
        }
    }
    public static void goUserActivity(String userName, Context context){
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(context.getString(R.string.user_page_username_name), userName);
        context.startActivity(intent);
    }
}
