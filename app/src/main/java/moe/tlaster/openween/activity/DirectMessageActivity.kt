package moe.tlaster.openween.activity

import android.graphics.Color
import android.net.Uri
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.bumptech.glide.Glide

import java.util.ArrayList
import java.util.Timer
import java.util.TimerTask

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.bindView
import de.hdodenhof.circleimageview.CircleImageView
import moe.tlaster.openween.R
import moe.tlaster.openween.adapter.DirectMessageAdapter
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.api.Entity
import moe.tlaster.openween.core.api.directMessage.DirectMessage
import moe.tlaster.openween.core.model.directmessage.DirectMessageListModel
import moe.tlaster.openween.core.model.directmessage.DirectMessageModel
import moe.tlaster.openween.core.model.user.UserModel
import okhttp3.Call

class DirectMessageActivity : BaseActivity() {
    val mUserHeader: View by bindView(R.id.direct_message_user_header)
    val mEditText: AppCompatEditText by bindView(R.id.direct_message_edit_text)
    val mListLayout: View by bindView(R.id.direct_message_list_layout)
    private var mUser: UserModel? = null
    private var mTimer: Timer? = null
    private var mRecyclerView: RecyclerView? = null
    private var mRefresh: SwipeRefreshLayout? = null
    private var mAdapter: DirectMessageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_direct_message)
        mUser = intent.extras.getParcelable<UserModel>(getString(R.string.user_item_name))
        Glide.with(this).load(mUser!!.avatarLarge).into(mUserHeader.findViewById(R.id.user_img) as CircleImageView)
        (mUserHeader.findViewById(R.id.user_name) as TextView).text = mUser!!.screenName
        (mUserHeader.findViewById(R.id.user_name) as TextView).setTextColor(Color.WHITE)
        (mUserHeader.findViewById(R.id.user_sub_text) as TextView).text = mUser!!.description
        (mUserHeader.findViewById(R.id.user_sub_text) as TextView).setTextColor(Color.WHITE)
        (mUserHeader.findViewById(R.id.user_sub_text) as TextView).setLines(1)
        mRecyclerView = mListLayout.findViewById(R.id.recyclerView) as RecyclerView
        mRefresh = mListLayout.findViewById(R.id.refresher) as SwipeRefreshLayout
        mRefresh!!.setOnRefreshListener { this.loadMore() }
        mRefresh!!.isEnabled = false
        mAdapter = DirectMessageAdapter()
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
        mRecyclerView!!.adapter = mAdapter
        refresh()
        mTimer = Timer()
        mTimer!!.scheduleAtFixedRate(GetNewTask(), (30 * 1000).toLong(), (30 * 1000).toLong())
    }

    private fun refresh() {
        if (TextUtils.isEmpty(Entity.accessToken)) return
        DirectMessage.getConversation(mUser!!.id.toString(), count = 20, callback = object : JsonCallback<DirectMessageListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                Toast.makeText(this@DirectMessageActivity, "加载失败", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(response: DirectMessageListModel, id: Int) {
                mAdapter!!.setNewData(response.directMessages!!.reversed())
                mRecyclerView!!.scrollToPosition(response.directMessages!!.size - 1)
                mRefresh!!.isEnabled = true
            }
        })
    }

    private fun loadMore() {
        val id = mAdapter!!.data[0].id
        DirectMessage.getConversation(mUser!!.id.toString(), count = 20, max_id = id, callback = object : JsonCallback<DirectMessageListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                Toast.makeText(this@DirectMessageActivity, "加载失败", Toast.LENGTH_SHORT).show()
                mRefresh!!.isRefreshing = false
            }

            override fun onResponse(response: DirectMessageListModel, id: Int) {
                val modelList = mAdapter!!.data
                response.directMessages = response.directMessages!!.subList(1, response.directMessages!!.lastIndex)
                for (directMessageModel in response.directMessages!!) {
                    modelList.add(0, directMessageModel)
                }
                mAdapter!!.setNewData(modelList)
                mRecyclerView!!.scrollToPosition(response.directMessages!!.size - 1)
                mRefresh!!.isRefreshing = false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mTimer!!.cancel()
    }

    @OnClick(R.id.direct_message_send_button)
    fun send() {
        val text = mEditText.text.toString()
        mEditText.setText("")
        DirectMessage.send(mUser!!.id, text = text, callback = object : JsonCallback<DirectMessageModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {
                Toast.makeText(this@DirectMessageActivity, "发送失败", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(response: DirectMessageModel, id: Int) {
                mAdapter!!.addData(object : ArrayList<DirectMessageModel>() {
                    init {
                        add(response)
                    }
                })
            }
        })
    }

    private fun getNew() {
        val id = mAdapter!!.data[mAdapter!!.data.size - 1].id
        DirectMessage.getConversation(mUser!!.id.toString(), max_id = id, count = 20, callback = object : JsonCallback<DirectMessageListModel>() {
            override fun onError(call: Call, e: Exception, id: Int) {

            }

            override fun onResponse(response: DirectMessageListModel, id: Int) {
                mAdapter!!.addData(response.directMessages)
                mRecyclerView!!.scrollToPosition(mAdapter!!.data.size)
            }
        })
    }

    internal inner class GetNewTask : TimerTask() {
        override fun run() {
            getNew()
        }
    }
}
