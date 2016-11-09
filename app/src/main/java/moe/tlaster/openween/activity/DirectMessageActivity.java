package moe.tlaster.openween.activity;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import moe.tlaster.openween.R;
import moe.tlaster.openween.adapter.DirectMessageAdapter;
import moe.tlaster.openween.common.helpers.JsonCallback;
import moe.tlaster.openween.common.helpers.Reverse;
import moe.tlaster.openween.core.api.Entity;
import moe.tlaster.openween.core.api.directMessage.DirectMessage;
import moe.tlaster.openween.core.model.directmessage.DirectMessageListModel;
import moe.tlaster.openween.core.model.directmessage.DirectMessageModel;
import moe.tlaster.openween.core.model.user.UserModel;
import okhttp3.Call;

public class DirectMessageActivity extends BaseActivity {

    @BindView(R.id.direct_message_user_header)
    public View mUserHeader;
    @BindView(R.id.direct_message_edit_text)
    public AppCompatEditText mEditText;
    @BindView(R.id.direct_message_list_layout)
    public View mListLayout;
    private UserModel mUser;
    private Timer mTimer;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefresh;
    private DirectMessageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_message);
        ButterKnife.bind(this);
        mUser = getIntent().getExtras().getParcelable(getString(R.string.user_item_name));
        Glide.with(this).load(mUser.getAvatarLarge()).into((CircleImageView) mUserHeader.findViewById(R.id.user_img));
        ((TextView) mUserHeader.findViewById(R.id.user_name)).setText(mUser.getScreenName());
        ((TextView) mUserHeader.findViewById(R.id.user_name)).setTextColor(Color.WHITE);
        ((TextView) mUserHeader.findViewById(R.id.user_sub_text)).setText(mUser.getDescription());
        ((TextView) mUserHeader.findViewById(R.id.user_sub_text)).setTextColor(Color.WHITE);
        ((TextView) mUserHeader.findViewById(R.id.user_sub_text)).setLines(1);
        mRecyclerView = (RecyclerView) mListLayout.findViewById(R.id.recyclerView);
        mRefresh = (SwipeRefreshLayout) mListLayout.findViewById(R.id.refresher);
        mRefresh.setOnRefreshListener(this::loadMore);
        mRefresh.setEnabled(false);
        mAdapter = new DirectMessageAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        refresh();
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new GetNewTask(), 30*1000, 30*1000);
    }

    private void refresh() {
        if (TextUtils.isEmpty(Entity.getAccessToken())) return;
        DirectMessage.getConversation(String.valueOf(mUser.getID()), 20, 0, new JsonCallback<DirectMessageListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(DirectMessageActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(DirectMessageListModel response, int id) {
                mAdapter.setNewData(Stream.of(response.getDirectMessages()).custom(new Reverse<>()).collect(Collectors.toList()));
                mRecyclerView.scrollToPosition(response.getDirectMessages().size() - 1);
                mRefresh.setEnabled(true);
            }
        });
    }

    private void loadMore() {
        long id = mAdapter.getData().get(0).getID();
        DirectMessage.getConversation(String.valueOf(mUser.getID()), 20, id, new JsonCallback<DirectMessageListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(DirectMessageActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                mRefresh.setRefreshing(false);
            }

            @Override
            public void onResponse(DirectMessageListModel response, int id) {
                List<DirectMessageModel> modelList = mAdapter.getData();
                response.getDirectMessages().remove(0);
                for (DirectMessageModel directMessageModel : response.getDirectMessages()) {
                    modelList.add(0, directMessageModel);
                }
                mAdapter.setNewData(modelList);
                mRecyclerView.scrollToPosition(response.getDirectMessages().size() - 1);
                mRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }

    @OnClick(R.id.direct_message_send_button)
    public void send() {
        String text = mEditText.getText().toString();
        mEditText.setText("");
        DirectMessage.send(mUser.getID(), text, new JsonCallback<DirectMessageModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(DirectMessageActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(DirectMessageModel response, int id) {
                mAdapter.addData(new ArrayList<DirectMessageModel>(){{add(response);}});
            }
        });
    }

    private void getNew() {
        long id = mAdapter.getData().get(mAdapter.getData().size() - 1).getID();
        DirectMessage.getConversation(String.valueOf(mUser.getID()), id, 0, 20, 1, new JsonCallback<DirectMessageListModel>() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(DirectMessageListModel response, int id) {
                mAdapter.addData(response.getDirectMessages());
                mRecyclerView.scrollToPosition(mAdapter.getData().size());
            }
        });
    }

    class GetNewTask extends TimerTask {
        @Override
        public void run() {
            getNew();
        }
    }
}
