package moe.tlaster.openween.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.AppCompatTextView
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.transition.Slide
import android.util.Log
import android.util.SparseArray
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.Toast
import butterknife.*

import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.goka.flickableview.FlickableImageView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.view.IconicsCompatButton

import java.io.File
import java.io.UnsupportedEncodingException
import java.util.ArrayList
import java.util.HashMap
import java.util.LinkedHashMap
import java.util.Objects
import java.util.TreeMap

import me.nereo.multi_image_selector.MultiImageSelector
import me.nereo.multi_image_selector.MultiImageSelectorActivity
import moe.tlaster.openween.R
import moe.tlaster.openween.common.StaticResource
import moe.tlaster.openween.common.controls.WeiboImageList
import moe.tlaster.openween.common.entities.PostWeiboType
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.core.api.comments.Comments
import moe.tlaster.openween.core.api.statuses.PostWeibo
import moe.tlaster.openween.core.model.EmotionModel
import moe.tlaster.openween.core.model.comment.CommentModel
import moe.tlaster.openween.core.model.status.MessageModel
import moe.tlaster.openween.core.model.status.PictureModel
import moe.tlaster.openween.core.model.types.RepostType
import okhttp3.Call
import okhttp3.Response

class PostWeiboActivity : BaseActivity() {

    private var mAdapter: BaseQuickAdapter<String, BaseViewHolder>? = null
    private val REQUEST_IMAGE = 0
    private val REQUEST_READ_EXTERNAL_STORAGE = 1
    private var mDialog: MaterialDialog? = null
    private var mMaxImageCount = 9
    private var mType: PostWeiboType? = null
    val mEditText: AppCompatEditText by bindView(R.id.post_weibo_edit_text)
    val mRoot: CoordinatorLayout by bindView(R.id.post_weibo_root)
    val mTextCount: AppCompatTextView by bindView(R.id.post_weibo_textcount)
    val mImageRecycler: RecyclerView by bindView(R.id.post_weibo_image_recycler)
    val mEmotionLayout: LinearLayout by bindView(R.id.post_weibo_emotion_layout)
    val mEmotionViewPager: ViewPager by bindView(R.id.post_weibo_emotion_viewPager)
    val mEmotionTab: TabLayout by bindView(R.id.post_weibo_emotion_tab)
    val mPostWeiboMain: RelativeLayout by bindView(R.id.post_weibo_main_content)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_weibo)
        setupWindowAnimations()
        mEditText.requestFocusFromTouch()
        mEditText.requestFocus()
        setEmotion()
        mAdapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.weibo_image_list_itemtemplate, null) {
            override fun convert(baseViewHolder: BaseViewHolder, path: String) {
                val view = baseViewHolder.getView<FlickableImageView>(R.id.weibo_image_list_item)
                view.maxHeight = 100
                view.maxWidth = 100
                Glide.with(this@PostWeiboActivity).load(File(path)).centerCrop().into(view)
                baseViewHolder.addOnLongClickListener(R.id.weibo_image_list_item)
            }
        }
        mImageRecycler.adapter = mAdapter
        mImageRecycler.layoutManager = GridLayoutManager(this, 9)
        mImageRecycler.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                val spacing = 20
                val spanCount = 9
                val position = parent.getChildAdapterPosition(view)
                val column = position % spanCount
                outRect.left = spacing - column * spacing / spanCount
                outRect.right = (column + 1) * spacing / spanCount
                if (position < spanCount) {
                    outRect.top = spacing
                }
                outRect.bottom = spacing
            }
        })
        mImageRecycler.addOnItemTouchListener(object : OnItemChildClickListener() {
            override fun SimpleOnItemChildClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {

            }

            override fun onItemChildLongClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                when (view!!.id) {
                    R.id.weibo_image_list_item -> MaterialDialog.Builder(this@PostWeiboActivity)
                            .title("注意")
                            .content("要删除图片吗")
                            .positiveText("是")
                            .negativeText("否")
                            .onPositive { dialog, which -> mAdapter!!.remove(position) }
                            .show()
                    else -> {
                    }
                }
            }
        })
        mType = intent.getSerializableExtra(getString(R.string.post_weibo_type_name)) as PostWeiboType
        mEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                mTextCount.text = (140 - getTextCount(s.toString())).toString()
            }
        })
        initData()
    }

    private fun getTextCount(value: String): Int {
        try {
            return (value.toByteArray(charset("GB2312")).size + 1) / 2
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            return -1
        }

    }


    private fun setEmotion() {
        if (StaticResource.emotions != null && StaticResource.emotions!!.isNotEmpty()) {
            val map = StaticResource.emotions!!.groupBy { it.category }
            mEmotionViewPager.adapter = object : PagerAdapter() {
                override fun getCount(): Int {
                    return map.keys.size
                }

                override fun instantiateItem(container: ViewGroup, position: Int): Any {
                    val itemTemplate = LayoutInflater.from(this@PostWeiboActivity).inflate(R.layout.list_layout, container, false)
                    itemTemplate.findViewById(R.id.refresher).isEnabled = false
                    val column = 8
                    val recyclerView = itemTemplate.findViewById(R.id.recyclerView) as RecyclerView
                    recyclerView.layoutManager = GridLayoutManager(this@PostWeiboActivity, column)
                    recyclerView.adapter = object : BaseQuickAdapter<EmotionModel, BaseViewHolder>(R.layout.emotion_image, map[map.keys.toTypedArray()[position]]) {
                        override fun convert(baseViewHolder: BaseViewHolder, emotionModel: EmotionModel) {
                            baseViewHolder.setImageBitmap(R.id.emotion_img, BitmapFactory.decodeFile(emotionModel.url))
                            baseViewHolder.getView<View>(R.id.emotion_img).setOnClickListener { view ->
                                val position = mEditText.selectionStart
                                mEditText.text = mEditText.text.insert(position, emotionModel.value)
                                mEditText.setSelection(position + emotionModel.value!!.length)
                            }
                        }
                    }
                    container.addView(itemTemplate)
                    return itemTemplate
                }

                override fun isViewFromObject(view: View, `object`: Any): Boolean {
                    return view === `object`
                }

                override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                    container.removeView(`object` as View)
                }
            }
            mEmotionTab.setupWithViewPager(mEmotionViewPager)
            for (i in 0..mEmotionTab.tabCount - 1) {
                val tab = mEmotionTab.getTabAt(i)
                tab!!.text = map.keys.toTypedArray()[i]
            }
        }
    }


    private fun initData() {
        if (mType == PostWeiboType.NewPost) return
        mMaxImageCount = 1
        if (!intent.hasExtra(getString(R.string.post_weibo_data_name))) return
        val data = intent.getStringExtra(getString(R.string.post_weibo_data_name))
        if (TextUtils.isEmpty(data)) return
        mEditText.setText(data)
        if (mType == PostWeiboType.RePost)
            mEditText.setSelection(0)
        else
            mEditText.setSelection(data.length)
    }

    private fun setupWindowAnimations() {
        val slide = Slide()
        slide.duration = 500
        slide.slideEdge = Gravity.BOTTOM
        window.enterTransition = slide
        window.exitTransition = slide
    }

    @OnClick(R.id.post_weibo_add_emotion_button)
    fun addEmotion(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
    }

    private fun showImageSelector() {
        MultiImageSelector.create()
                .count(mMaxImageCount)
                .origin(ArrayList(mAdapter!!.data))
                .start(this, REQUEST_IMAGE)
    }

    @OnClick(R.id.post_weibo_add_image_button)
    fun addImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            requestPermission()
        else
            showImageSelector()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_READ_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showImageSelector()
                } else {
                    MaterialDialog.Builder(this@PostWeiboActivity)
                            .title("注意")
                            .content("添加图片需要允许权限")
                            .positiveText("允许")
                            .negativeText("拒绝")
                            .onPositive { dialog, which -> requestPermission() }
                            .show()
                }
            }
            else -> {
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != REQUEST_IMAGE || resultCode != Activity.RESULT_OK) return
        mAdapter!!.setNewData(data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT))
        if (mAdapter!!.data.size > 0)
            mImageRecycler.visibility = View.VISIBLE
        else
            mImageRecycler.visibility = View.GONE
    }

    @OnClick(R.id.post_weibo_send_button)
    fun send() {
        if (TextUtils.isEmpty(mEditText.text)) return
        if (getTextCount(mEditText.text.toString()) < 0) {
            MaterialDialog.Builder(this@PostWeiboActivity)
                    .title("超出140字限制")
                    .content("是否删除多余字符？")
                    .positiveText("是")
                    .negativeText("否")
                    .onPositive { dialog, which -> send() }
                    .show()
            return
        }
        mDialog = MaterialDialog.Builder(this)
                .title("正在发送")
                .content(getString(R.string.please_wait))
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .cancelable(false)
                .canceledOnTouchOutside(false)
                .show()
        when (mType) {
            PostWeiboType.NewPost -> newPost()
            PostWeiboType.RePost -> rePost()
            PostWeiboType.Comment -> comment()
            PostWeiboType.ReplyComment -> replyComment()
            else -> {
            }
        }
    }

    private fun replyComment() {
        if (hasImage()) {
            upload(mAdapter!!.data, object : PostWeiboCallback<Collection<PictureModel>>() {
                override fun onResponse(data: Collection<PictureModel>) {
                    Comments.replyWithPic(intent.extras.getLong(getString(R.string.post_weibo_id_name)), intent.extras.getLong(getString(R.string.post_weibo_cid_name)), mEditText.text.toString(), pid = data.iterator().next().picID!!, callback = PostWeiboJsonCallback<CommentModel>())
                }
            })
        } else {
            Comments.reply(intent.extras.getLong(getString(R.string.post_weibo_id_name)), intent.extras.getLong(getString(R.string.post_weibo_cid_name)), mEditText.text.toString(), callback = PostWeiboJsonCallback<CommentModel>())
        }
    }

    private fun comment() {
        if (hasImage()) {
            upload(mAdapter!!.data, object : PostWeiboCallback<Collection<PictureModel>>() {
                override fun onResponse(data: Collection<PictureModel>) {
                    Comments.postCommentWithPic(intent.extras.getLong(getString(R.string.post_weibo_id_name)), mEditText.text.toString(), pid = data.iterator().next().picID!!, callback = PostWeiboJsonCallback<CommentModel>())
                }
            })
        } else {
            Comments.postComment(intent.extras.getLong(getString(R.string.post_weibo_id_name)), mEditText.text.toString(), callback = PostWeiboJsonCallback<CommentModel>())
        }
    }

    private fun rePost() {
        if (hasImage()) {
            upload(mAdapter!!.data, object : PostWeiboCallback<Collection<PictureModel>>() {
                override fun onResponse(data: Collection<PictureModel>) {
                    PostWeibo.repostWithPic(intent.extras.getLong(getString(R.string.post_weibo_id_name)), mEditText.text.toString(), pid = data.iterator().next().picID!!, callback = PostWeiboJsonCallback<MessageModel>())
                }
            })
        } else {
            PostWeibo.repost(intent.extras.getLong(getString(R.string.post_weibo_id_name)), mEditText.text.toString(), RepostType.None, PostWeiboJsonCallback<MessageModel>())
        }
    }

    private fun newPost() {
        if (hasImage()) {
            upload(mAdapter!!.data, object : PostWeiboCallback<Collection<PictureModel>>() {
                override fun onResponse(data: Collection<PictureModel>) {
                    PostWeibo.postWithMultiPics(if (mEditText.text.isNotEmpty()) mEditText.text.toString() else "分享图片", TextUtils.join(",", data.map { it.picID }), callback = PostWeiboJsonCallback<MessageModel>())
                }
            })
        } else if (mEditText.text.isNotEmpty()) {
            PostWeibo.post(mEditText.text.toString(), callback = PostWeiboJsonCallback<MessageModel>())
        }
    }

    private fun hasImage(): Boolean {
        return mAdapter!!.data.size > 0
    }

    private fun upload(data: List<String>, callback: PostWeiboCallback<Collection<PictureModel>>) {
        val jsonCallback = object : JsonCallback<PictureModel>() {
            private val mMap = TreeMap<Int, PictureModel>()
            override fun onError(call: Call, e: Exception, id: Int) {
                callback.onError()
            }

            override fun onResponse(response: PictureModel, id: Int) {
                mMap.put(id, response)
                if (mMap.size == data.size) callback.onResponse(mMap.values)
            }
        }
        for (i in data.indices) {
            val finalI = i
            PostWeibo.uploadPicture(File(data[i]), object : JsonCallback<PictureModel>() {
                override fun onError(call: Call, e: Exception, id: Int) {
                    jsonCallback.onError(call, e, id)
                }

                override fun onResponse(response: PictureModel, id: Int) {
                    jsonCallback.onResponse(response, finalI)
                }
            })
        }
    }

    private fun onError() {
        onAction("发送失败")
    }

    private fun onSuccess() {
        onAction("发送成功")
        finish()
    }

    private fun onAction(content: String) {
        mDialog!!.dismiss()
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
    }

    private inner class PostWeiboJsonCallback<T> : JsonCallback<T>() {
        override fun onError(call: Call, e: Exception, id: Int) {
            this@PostWeiboActivity.onError()
        }

        override fun onResponse(response: T, id: Int) {
            this@PostWeiboActivity.onSuccess()
        }
    }

    private abstract inner class PostWeiboCallback<T> {
        internal fun onError() {
            this@PostWeiboActivity.onError()
        }

        internal abstract fun onResponse(data: T)
    }
}
