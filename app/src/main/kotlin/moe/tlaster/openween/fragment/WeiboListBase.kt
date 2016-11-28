package moe.tlaster.openween.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import moe.tlaster.openween.R
import moe.tlaster.openween.adapter.BaseModelAdapter
import moe.tlaster.openween.common.Event
import moe.tlaster.openween.common.SimpleDividerItemDecoration
import moe.tlaster.openween.common.StaticResource
import moe.tlaster.openween.common.controls.Pivot
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.api.Entity
import moe.tlaster.openween.core.model.BaseModel
import okhttp3.Call

/**
 * Created by Tlaster on 2016/9/10.
 */
abstract class WeiboListBase<T> : Pivot.PivotItemFragment() {
    protected interface Callback<T> {
        fun onError(e: Exception)
        fun onResponse(response: T, totalCount: Int)
    }
    protected abstract inner class WeiboListCallback<T> : JsonCallback<T>(){
        override fun onError(call: Call?, e: Exception?, id: Int) {
            if (context?.resources == null) return
            Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show()
            mSwipeRefreshLayout?.isRefreshing = false
        }
    }
    var OnRefresh = Event<View>()
    protected var mRecyclerView: RecyclerView? = null
    protected var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    protected var mAdapter: BaseQuickAdapter<T, BaseViewHolder>? = null
    private var mTotalCount = 0
    protected var mLoadCount = 20
    protected var mPage = 1
    protected val contentView: Int
        get() = R.layout.list_layout
    protected val recyclerView: Int
        get() = R.id.recyclerView
    protected val swipeRefreshLayout: Int
        get() = R.id.refresher
    protected val layoutManager: RecyclerView.LayoutManager
        get() = LinearLayoutManager(context)

    protected abstract fun initAdapter(): BaseQuickAdapter<T, BaseViewHolder>

    private fun HasMore(): Boolean {
        return mAdapter?.data?.size!! < mTotalCount
    }

    fun toTop() {
        mRecyclerView?.scrollToPosition(0)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(contentView, container, false)
        mRecyclerView = view.findViewById(recyclerView) as RecyclerView
        mSwipeRefreshLayout = view.findViewById(swipeRefreshLayout) as SwipeRefreshLayout
        mSwipeRefreshLayout?.setOnRefreshListener{
            this.refresh()
            OnRefresh.invoke(view)
        }
        mRecyclerView?.layoutManager = layoutManager
        mAdapter = initAdapter()
        mAdapter?.openLoadAnimation()
        mAdapter?.openLoadMore(20)
        mRecyclerView?.adapter = mAdapter
        mAdapter?.setOnLoadMoreListener { mRecyclerView?.post { this.loadMore() } }
        mRecyclerView?.addItemDecoration(SimpleDividerItemDecoration(context))
        refresh()
        return view
    }

    private fun loadMore() {
        if (!HasMore() || TextUtils.isEmpty(Entity.accessToken)) {
            mAdapter?.loadComplete()
            mAdapter?.addFooterView(getLayoutInflater(null).inflate(R.layout.not_loading, mRecyclerView?.parent as ViewGroup, false))
            return
        }
        loadMoreOverride(object : Callback<List<T>> {
            override fun onError(e: Exception) {
                //mAdapter.showLoadMoreFailedView();//TODO:Show refresh failed
                Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(response: List<T>, totalCount: Int) {
                mAdapter?.addData(response)
                mTotalCount = totalCount
            }
        })
    }

    fun refresh() {
        if (TextUtils.isEmpty(Entity.accessToken)) return
        mSwipeRefreshLayout?.post { mSwipeRefreshLayout?.isRefreshing = true }
        mPage = 1
        refreshOverride(object : Callback<List<T>> {
            override fun onError(e: Exception) {
                //mAdapter.showLoadMoreFailedView();//TODO:Show refresh failed
                Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show()
                mSwipeRefreshLayout?.isRefreshing = false
            }

            override fun onResponse(response: List<T>, totalCount: Int) {
                mAdapter?.setNewData(response)
                mTotalCount = totalCount
                mSwipeRefreshLayout?.isRefreshing = false
            }
        })
    }

    protected abstract fun loadMoreOverride(callback: Callback<List<T>>)
    protected abstract fun refreshOverride(callback: Callback<List<T>>)
}
