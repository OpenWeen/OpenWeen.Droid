package moe.tlaster.openween.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jaeger.ninegridimageview.NineGridImageView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import moe.tlaster.openween.R;
import moe.tlaster.openween.activity.DetailActivity;
import moe.tlaster.openween.activity.MainActivity;
import moe.tlaster.openween.activity.PostWeiboActivity;
import moe.tlaster.openween.activity.SplashActivity;
import moe.tlaster.openween.activity.UserActivity;
import moe.tlaster.openween.common.controls.WeiboTextBlock;
import moe.tlaster.openween.common.entities.PostWeiboType;
import moe.tlaster.openween.common.helpers.WeiboCardHelper;
import moe.tlaster.openween.core.model.BaseModel;
import moe.tlaster.openween.core.model.comment.CommentModel;
import moe.tlaster.openween.core.model.status.MessageModel;
import moe.tlaster.openween.core.model.user.UserModel;

/**
 * Created by Asahi on 2016/9/23.
 */

public class BaseModelAdapter<T extends BaseModel> extends BaseQuickAdapter<T> {

    private boolean mIsEnableRepost;

    public BaseModelAdapter() {
        this(true);
    }

    public BaseModelAdapter(boolean isEnableRepost) {
        super(R.layout.base_model_template, new ArrayList<>());
        mIsEnableRepost = isEnableRepost;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BaseModel baseModel) {
        View baseView = baseViewHolder.getConvertView();
        WeiboCardHelper.setData(baseView, baseModel, mContext, mIsEnableRepost);
        if (baseModel instanceof MessageModel)
        {
            baseView.findViewById(R.id.weibo_content_container).findViewById(R.id.weibo_content).setOnClickListener(view -> goDetail((MessageModel) baseModel, baseView));
            if (((MessageModel) baseModel).getRetweetedStatus() != null)
                baseView.findViewById(R.id.weibo_repost_container).findViewById(R.id.weibo_content).setOnClickListener(view -> goDetail(((MessageModel) baseModel).getRetweetedStatus(), baseView));
        }
        if (baseModel instanceof CommentModel && ((CommentModel) baseModel).getStatus() != null)
            baseView.findViewById(R.id.weibo_repost_container).findViewById(R.id.weibo_content).setOnClickListener(view -> goDetail(((CommentModel) baseModel).getStatus(), baseView));
    }

    private void goDetail(MessageModel baseModel, View baseView) {
        Intent i = new Intent(mContext, DetailActivity.class);
        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, baseView, mContext.getString(R.string.base_model_template_name));
        i.putExtra(mContext.getString(R.string.detail_message_model_name), baseModel);
        mContext.startActivity(i, transitionActivityOptions.toBundle());
    }

}
