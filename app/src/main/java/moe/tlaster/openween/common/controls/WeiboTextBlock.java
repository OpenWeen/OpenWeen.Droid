package moe.tlaster.openween.common.controls;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import moe.tlaster.openween.common.StaticResource;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.core.api.shortUrl.ShortUrl;
import moe.tlaster.openween.core.model.status.PictureModel;
import moe.tlaster.openween.core.model.url.UrlInfoListModel;
import moe.tlaster.openween.core.model.url.UrlInfoModel;
import okhttp3.Call;

/**
 * Created by Asahi on 2016/9/24.
 */

public class WeiboTextBlock extends TextView {
    
    public interface WeiboTextCallback{
        void call(String data);
    }
    
    private static final String AT = "@[^,\uff0c\uff1a:\\s@]+";
    private static final String TOPIC = "#[^#]+#";
    private static final String EMOJI = "\\[[\\w]+\\]";
    private static final String URL = "http://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    private static final String REGEX = "(" + AT + ")|(" + TOPIC + ")|(" + EMOJI + ")|(" + URL + ")";
    private static final Pattern pattern = Pattern.compile(REGEX);
    private WeiboTextCallback mUserClicked;
    private WeiboTextCallback mLinkClicked;
    private WeiboTextCallback mTopicClicked;

    public WeiboTextCallback getUserClicked() {
        return mUserClicked;
    }

    public void setUserClicked(WeiboTextCallback userClicked) {
        mUserClicked = userClicked;
    }

    public WeiboTextCallback getLinkClicked() {
        return mLinkClicked;
    }

    public void setLinkClicked(WeiboTextCallback linkClicked) {
        mLinkClicked = linkClicked;
    }

    public WeiboTextCallback getTopicClicked() {
        return mTopicClicked;
    }

    public void setTopicClicked(WeiboTextCallback topicClicked) {
        mTopicClicked = topicClicked;
    }

    public WeiboTextBlock(Context context) {
        super(context);
    }

    public WeiboTextBlock(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WeiboTextBlock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        SpannableString spannableString;
        int index = text.toString().indexOf("\u5168\u6587\uff1a http://m.weibo.cn/");
        if (index != -1){
            int length = index + 2;
            spannableString = new SpannableString(text.toString().substring(0, length));
            ForegroundColorSpan span = new ForegroundColorSpan(Color.BLUE);
            spannableString.setSpan(span, index, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            spannableString = new SpannableString(text);
        }
        Matcher matcher = pattern.matcher(spannableString);
        if (matcher.find()) {
            setMovementMethod(LinkMovementMethod.getInstance());
            matcher.reset();
        }

        while (matcher.find()) {
            final String at = matcher.group(1);
            final String topic = matcher.group(2);
            String emoji = matcher.group(3);
            final String url = matcher.group(4);
            if (at != null) {
                int start = matcher.start(1);
                int end = start + at.length();
                ExClickableSpan clickableSpan = new ExClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        if (mUserClicked != null)
                            mUserClicked.call(at.substring(1));
                    }
                };
                spannableString.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            if (topic != null) {
                int start = matcher.start(2);
                int end = start + topic.length();
                ExClickableSpan clickableSpan = new ExClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        if (mTopicClicked != null)
                            mTopicClicked.call(topic);
                    }
                };
                spannableString.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            if (emoji != null) {
                int start = matcher.start(3);
                int end = start + emoji.length();
                try {
                    String filePath = Stream.of(StaticResource.getEmotions()).filter(item -> Objects.equals(item.getValue(), emoji)).findFirst().get().getUrl();
                    Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                    if (bitmap != null) {
                        int size = (int) getTextSize();
                        bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
                        ImageSpan imageSpan = new ImageSpan(getContext(), bitmap, ImageSpan.ALIGN_BASELINE);
                        spannableString.setSpan(imageSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                } catch (Exception e) {
                }
            }

            if (url != null) {
                int start = matcher.start(4);
                int end = start + url.length();
                ExClickableSpan clickableSpan = new ExClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        if(mLinkClicked != null)
                            mLinkClicked.call(url);
                        ShortUrl.info(new JsonCallback<UrlInfoListModel>() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                openLink(url);
                            }
                            @Override
                            public void onResponse(UrlInfoListModel response, int id) {
                                UrlInfoModel item = response.getUrls().get(0);
                                switch (item.getType()){
                                    case 39: {
                                        try {
                                            String picid = item.getAnnotations().get(0).getItem().getPicIds()[0];
                                            if (!TextUtils.isEmpty(picid)) {
                                                Intent intent = new Intent(getContext(), WeiboImageList.class);
                                                intent.putStringArrayListExtra(WeiboImageList.INTENTNAME, new ArrayList<String>(){{add("http://ww1.sinaimg.cn/large/"+picid+".jpg");}});
                                                getContext().startActivity(intent);
                                            }
                                        } catch (Exception e) {
                                            //Do nothing
                                            openLink(item.getUrlLong());
                                        }
                                    }
                                    break;
                                    default:
                                        openLink(item.getUrlLong());
                                        break;
                                }
                            }
                        }, url);
                    }
                };
                spannableString.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        super.setText(spannableString, type);
    }

    private void openLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        getContext().startActivity(intent);
    }
}
