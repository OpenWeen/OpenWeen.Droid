package moe.tlaster.openween.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.Slide;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.goka.flickableview.FlickableImageView;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.view.IconicsCompatButton;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import moe.tlaster.openween.R;
import moe.tlaster.openween.common.StaticResource;
import moe.tlaster.openween.common.controls.WeiboImageList;
import moe.tlaster.openween.common.entities.PostWeiboType;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.core.api.comments.Comments;
import moe.tlaster.openween.core.api.statuses.PostWeibo;
import moe.tlaster.openween.core.model.EmotionModel;
import moe.tlaster.openween.core.model.status.MessageModel;
import moe.tlaster.openween.core.model.status.PictureModel;
import moe.tlaster.openween.core.model.types.RepostType;
import okhttp3.Call;
import okhttp3.Response;

public class PostWeiboActivity extends BaseActivity {

    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;
    private final int REQUEST_IMAGE = 0;
    private final int REQUEST_READ_EXTERNAL_STORAGE = 1;
    private MaterialDialog mDialog;
    private int mMaxImageCount = 9;
    private PostWeiboType mType;
    @BindView(R.id.post_weibo_edit_text)
    public AppCompatEditText mEditText;
    @BindView(R.id.post_weibo_root)
    public CoordinatorLayout mRoot;
    @BindView(R.id.post_weibo_textcount)
    public AppCompatTextView mTextCount;
    @BindView(R.id.post_weibo_image_recycler)
    public RecyclerView mImageRecycler;
    @BindView(R.id.post_weibo_emotion_layout)
    public LinearLayout mEmotionLayout;
    @BindView(R.id.post_weibo_emotion_viewPager)
    public ViewPager mEmotionViewPager;
    @BindView(R.id.post_weibo_emotion_tab)
    public TabLayout mEmotionTab;
    @BindView(R.id.post_weibo_main_content)
    public RelativeLayout mPostWeiboMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_weibo);
        setupWindowAnimations();
        ButterKnife.bind(this);
        mEditText.requestFocusFromTouch();
        mEditText.requestFocus();
        setEmotion();
        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.weibo_image_list_itemtemplate, null) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, String path) {
                FlickableImageView view = baseViewHolder.getView(R.id.weibo_image_list_item);
                view.setMaxHeight(100);
                view.setMaxWidth(100);
                Glide.with(PostWeiboActivity.this).load(new File(path)).centerCrop().into(view);
                baseViewHolder.addOnLongClickListener(R.id.weibo_image_list_item);
            }
        };
        mImageRecycler.setAdapter(mAdapter);
        mImageRecycler.setLayoutManager(new GridLayoutManager(this, 9));
        mImageRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int spacing = 20;
                int spanCount = 9;
                int position = parent.getChildAdapterPosition(view);
                int column = position % spanCount;
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;
                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            }
        });
        mImageRecycler.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.weibo_image_list_item:
                        new MaterialDialog.Builder(PostWeiboActivity.this)
                                .title("注意")
                                .content("要删除图片吗")
                                .positiveText("是")
                                .negativeText("否")
                                .onPositive((dialog, which) -> mAdapter.remove(position))
                                .show();
                        break;
                    default:
                        break;
                }
            }
        });
        mType = (PostWeiboType) getIntent().getSerializableExtra(getString(R.string.post_weibo_type_name));
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTextCount.setText(String.valueOf(140 - getTextCount(s.toString())));
            }
        });
        initData();
    }

    private int getTextCount(String value) {
        try {
            return  (value.getBytes("GB2312").length + 1) / 2;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1;
        }
    }


    private void setEmotion() {
        if (StaticResource.getEmotions() != null && StaticResource.getEmotions().size() > 0) {
            Map<String, List<EmotionModel>> map = Stream.of(StaticResource.getEmotions()).collect(Collectors.groupingBy(EmotionModel::getCategory, LinkedHashMap::new, Collectors.toList()));
            mEmotionViewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return map.keySet().size();
                }
                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    View itemTemplate = LayoutInflater.from(PostWeiboActivity.this).inflate(R.layout.list_layout, container, false);
                    itemTemplate.findViewById(R.id.refresher).setEnabled(false);
                    int column = 8;
                    RecyclerView recyclerView = (RecyclerView) itemTemplate.findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new GridLayoutManager(PostWeiboActivity.this, column));
                    recyclerView.setAdapter(new BaseQuickAdapter<EmotionModel, BaseViewHolder>(R.layout.emotion_image, map.get(map.keySet().toArray()[position])) {
                        @Override
                        protected void convert(BaseViewHolder baseViewHolder, EmotionModel emotionModel) {
                            baseViewHolder.setImageBitmap(R.id.emotion_img, BitmapFactory.decodeFile(emotionModel.getUrl()));
                            baseViewHolder.getView(R.id.emotion_img).setOnClickListener(view -> {
                                int position = mEditText.getSelectionStart();
                                mEditText.setText(mEditText.getText().insert(position, emotionModel.getValue()));
                                mEditText.setSelection(position + emotionModel.getValue().length());
                            });
                        }
                    });
                    container.addView(itemTemplate);
                    return itemTemplate;
                }
                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    (container).removeView((View) object);
                }
            });
            mEmotionTab.setupWithViewPager(mEmotionViewPager);
            for (int i = 0; i < mEmotionTab.getTabCount(); i++) {
                TabLayout.Tab tab = mEmotionTab.getTabAt(i);
                tab.setText(map.keySet().toArray()[i].toString());
            }
        }
    }


    private void initData() {
        if (mType == PostWeiboType.NewPost) return;
        mMaxImageCount = 1;
        if (!getIntent().hasExtra(getString(R.string.post_weibo_data_name))) return;
        String data = getIntent().getStringExtra(getString(R.string.post_weibo_data_name));
        if (TextUtils.isEmpty(data)) return;
        mEditText.setText(data);
        if (mType == PostWeiboType.RePost) mEditText.setSelection(0);
        else mEditText.setSelection(data.length());
    }

    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setDuration(500);
        slide.setSlideEdge(Gravity.BOTTOM);
        getWindow().setEnterTransition(slide);
        getWindow().setExitTransition(slide);
    }

    @OnClick(R.id.post_weibo_add_emotion_button)
    public void addEmotion(View view) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this ,new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE , Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_READ_EXTERNAL_STORAGE);
    }

    private void showImageSelector() {
        MultiImageSelector.create()
                .count(mMaxImageCount)
                .origin(new ArrayList<>(mAdapter.getData()))
                .start(this, REQUEST_IMAGE);
    }

    @OnClick(R.id.post_weibo_add_image_button)
    public void addImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            requestPermission();
        else
            showImageSelector();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_EXTERNAL_STORAGE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showImageSelector();
                } else {
                    new MaterialDialog.Builder(PostWeiboActivity.this)
                            .title("注意")
                            .content("添加图片需要允许权限")
                            .positiveText("允许")
                            .negativeText("拒绝")
                            .onPositive((dialog, which) -> requestPermission())
                            .show();
                }
            }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQUEST_IMAGE || resultCode != RESULT_OK) return;
        mAdapter.setNewData(data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT));
        if (mAdapter.getData().size() > 0) mImageRecycler.setVisibility(View.VISIBLE);
        else mImageRecycler.setVisibility(View.GONE);
    }

    @OnClick(R.id.post_weibo_send_button)
    public void send() {
        if (TextUtils.isEmpty(mEditText.getText())) return;
        if (getTextCount(mEditText.getText().toString()) < 0){
            new MaterialDialog.Builder(PostWeiboActivity.this)
                    .title("超出140字限制")
                    .content("是否删除多余字符？")
                    .positiveText("是")
                    .negativeText("否")
                    .onPositive((dialog, which) -> send())
                    .show();
            return;
        }
        mDialog = new MaterialDialog.Builder(this)
                .title("正在发送")
                .content(getString(R.string.please_wait))
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .cancelable(false)
                .canceledOnTouchOutside(false)
                .show();
        switch (mType) {
            case NewPost: newPost();
                break;
            case RePost: rePost();
                break;
            case Comment: comment();
                break;
            case ReplyComment: replyComment();
                break;
            default:
                break;
        }
    }
    private void replyComment() {
        if (hasImage()) {
            upload(mAdapter.getData(), new PostWeiboCallback<Collection<PictureModel>>() {
                @Override
                void onResponse(Collection<PictureModel> data) {
                    Comments.replyWithPic(getIntent().getExtras().getLong(getString(R.string.post_weibo_id_name)), getIntent().getExtras().getLong(getString(R.string.post_weibo_cid_name)), mEditText.getText().toString(), data.iterator().next().getPicID(), new PostWeiboJsonCallback<>());
                }
            });
        } else {
            Comments.reply(getIntent().getExtras().getLong(getString(R.string.post_weibo_id_name)), getIntent().getExtras().getLong(getString(R.string.post_weibo_cid_name)), mEditText.getText().toString(), new PostWeiboJsonCallback<>());
        }
    }

    private void comment() {
        if (hasImage()) {
            upload(mAdapter.getData(), new PostWeiboCallback<Collection<PictureModel>>() {
                @Override
                void onResponse(Collection<PictureModel> data) {
                    Comments.postCommentWithPic(getIntent().getExtras().getLong(getString(R.string.post_weibo_id_name)), mEditText.getText().toString(), data.iterator().next().getPicID(), new PostWeiboJsonCallback<>());
                }
            });
        } else {
            Comments.postComment(getIntent().getExtras().getLong(getString(R.string.post_weibo_id_name)), mEditText.getText().toString(), new PostWeiboJsonCallback<>());
        }
    }

    private void rePost() {
        if (hasImage()) {
            upload(mAdapter.getData(), new PostWeiboCallback<Collection<PictureModel>>() {
                @Override
                void onResponse(Collection<PictureModel> data) {
                    PostWeibo.repostWithPic(getIntent().getExtras().getLong(getString(R.string.post_weibo_id_name)), mEditText.getText().toString(), data.iterator().next().getPicID(), RepostType.None, new PostWeiboJsonCallback<>());
                }
            });
        } else {
            PostWeibo.repost(getIntent().getExtras().getLong(getString(R.string.post_weibo_id_name)), mEditText.getText().toString(), RepostType.None , new PostWeiboJsonCallback<>());
        }
    }

    private void newPost() {
        if (hasImage()) {
            upload(mAdapter.getData(), new PostWeiboCallback<Collection<PictureModel>>() {
                @Override
                void onResponse(Collection<PictureModel> data) {
                    PostWeibo.postWithMultiPics(mEditText.getText().length() > 0 ? mEditText.getText().toString() : "分享图片", TextUtils.join(",", Stream.of(data).map(PictureModel::getPicID).toArray()), new PostWeiboJsonCallback<>());
                }
            });
        } else if (mEditText.getText().length() > 0) {
            PostWeibo.post(mEditText.getText().toString(), new PostWeiboJsonCallback<>());
        }
    }

    private boolean hasImage() {
        return mAdapter.getData().size() > 0;
    }

    private void upload(List<String> data, PostWeiboCallback<Collection<PictureModel>> callback) {
        JsonCallback<PictureModel> jsonCallback = new JsonCallback<PictureModel>() {
            private Map<Integer, PictureModel> mMap = new TreeMap<>();
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onError();
            }
            @Override
            public void onResponse(PictureModel response, int id) {
                mMap.put(id, response);
                if (mMap.size() == data.size()) callback.onResponse(mMap.values());
            }
        };
        for (int i = 0; i < data.size(); i++) {
            int finalI = i;
            PostWeibo.uploadPicture(new File(data.get(i)), new JsonCallback<PictureModel>(){
                @Override
                public void onError(Call call, Exception e, int id) {
                    jsonCallback.onError(call, e, id);
                }
                @Override
                public void onResponse(PictureModel response, int id) {
                    jsonCallback.onResponse(response, finalI);
                }
            });
        }
    }

    private void onError() {
        onAction("发送失败");
    }

    private void onSuccess() {
        onAction("发送成功");
        finish();
    }

    private void onAction(String content) {
        mDialog.dismiss();
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    private class PostWeiboJsonCallback<T> extends JsonCallback<T> {
        @Override
        public void onError(Call call, Exception e, int id) {
            PostWeiboActivity.this.onError();
        }

        @Override
        public void onResponse(T response, int id) {
            PostWeiboActivity.this.onSuccess();
        }
    }

    private abstract class PostWeiboCallback<T> {
        void onError(){
            PostWeiboActivity.this.onError();
        }
        abstract void onResponse(T data);
    }
}
