package moe.tlaster.openween.activity

import android.app.ActivityOptions
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar

import com.github.jorgecastilloprz.FABProgressCircle
import com.github.jorgecastilloprz.listeners.FABProgressListener
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.transitionseverywhere.AutoTransition
import com.transitionseverywhere.ChangeBounds
import com.transitionseverywhere.Transition
import com.transitionseverywhere.TransitionManager
import com.transitionseverywhere.TransitionSet
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.BitmapCallback

import net.hockeyapp.android.metrics.MetricsManager

import java.io.File
import java.util.ArrayList
import java.util.Arrays
import java.util.LinkedList
import java.util.Timer
import java.util.TimerTask

import de.hdodenhof.circleimageview.CircleImageView
import moe.tlaster.openween.R
import moe.tlaster.openween.common.StaticResource
import moe.tlaster.openween.common.helpers.DeviceHelper
import moe.tlaster.openween.common.helpers.JsonCallback
import moe.tlaster.openween.common.helpers.SettingHelper
import moe.tlaster.openween.common.service.NotificationService
import moe.tlaster.openween.core.api.Entity
import moe.tlaster.openween.core.api.user.Account
import moe.tlaster.openween.core.api.user.User
import moe.tlaster.openween.core.model.EmotionModel
import moe.tlaster.openween.core.model.LimitStatusModel
import moe.tlaster.openween.core.model.user.UserModel
import okhttp3.Call

import android.app.job.JobInfo.NETWORK_TYPE_ANY
import com.transitionseverywhere.TransitionSet.ORDERING_TOGETHER
import moe.tlaster.openween.common.bindView

class SplashActivity : BaseActivity() {
    val mFABProgressCircle: FABProgressCircle by bindView(R.id.splash_progress)
    val mCircleImageView: CircleImageView by bindView(R.id.splash_icon)
    private var mUser: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MetricsManager.register(application)
        val enableAnimate = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(getString(R.string.enable_animate_key), true)
        if (enableAnimate) {
            setContentView(R.layout.activity_splash)
            findViewById(R.id.splash_container).post {
                TransitionManager.beginDelayedTransition(this@SplashActivity.findViewById(R.id.splash_icon_container) as ViewGroup, TransitionSet()
                        .setOrdering(ORDERING_TOGETHER)
                        //.addTransition(new Slide(Gravity.LEFT))
                        .addTransition(ChangeBounds())
                        .setDuration(1000).setStartDelay(1000).addListener(object : Transition.TransitionListenerAdapter() {
                    override fun onTransitionEnd(transition: Transition?) {
                        allTransitionEnd()
                    }
                }))
                findViewById(R.id.splash_title).visibility = View.VISIBLE
            }
        } else {
            loadWithoutAnimate()
        }
    }

    private fun loadWithoutAnimate() {
        if (SettingHelper.getListSetting(this@SplashActivity, SettingHelper.ACCESSTOKEN) == null) {
            goLogin()
        } else {
            try {
                StaticResource.emotions = Arrays.asList(*Gson().fromJson(DeviceHelper.readFromFile(getExternalFilesDir(null)!!.path + File.separator + "emotion" + File.separator + "emotion.json"), Array<EmotionModel>::class.java))
            } catch (e: NullPointerException) {
            }

            Entity.accessToken = SettingHelper.getListSetting(this, SettingHelper.ACCESSTOKEN)!![0]
            Account.getLimitStatus(object : JsonCallback<LimitStatusModel>() {
                override fun onError(call: Call, e: Exception, id: Int) {
                    if (e.message!!.contains("403")) {
                        val list = LinkedList(Arrays.asList(*SettingHelper.getListSetting(this@SplashActivity, SettingHelper.ACCESSTOKEN)!!))
                        list.removeAt(0)
                        SettingHelper.setListSetting(this@SplashActivity, SettingHelper.ACCESSTOKEN, true, *list.toTypedArray())
                        goLogin()
                    }
                }

                override fun onResponse(response: LimitStatusModel, id: Int) {
                    Account.getUid(object : JsonCallback<String>() {
                        override fun onError(call: Call, e: Exception, id: Int) {
                            goHome()
                        }

                        override fun onResponse(response: String, id: Int) {
                            StaticResource.uid = JsonParser().parse(response).asJsonObject.get("uid").asLong
                            User.getUser(StaticResource.uid, object : JsonCallback<UserModel>() {
                                override fun onError(call: Call, e: Exception, id: Int) {
                                    goHome()
                                }

                                override fun onResponse(response: UserModel, id: Int) {
                                    mUser = response
                                    val i = Intent(this@SplashActivity, MainActivity::class.java)
                                    i.putExtra("user", mUser)
                                    startActivity(i)
                                    finish()
                                }
                            })
                        }
                    })
                }
            })
        }
    }

    private fun allTransitionEnd() {
        mFABProgressCircle.show()
        if (SettingHelper.getListSetting(this@SplashActivity, SettingHelper.ACCESSTOKEN) == null) {
            goLogin()
        } else {
            try {
                StaticResource.emotions = Arrays.asList(*Gson().fromJson(DeviceHelper.readFromFile(getExternalFilesDir(null)!!.path + File.separator + "emotion" + File.separator + "emotion.json"), Array<EmotionModel>::class.java))
            } catch (e: NullPointerException) {
            }

            Entity.accessToken = SettingHelper.getListSetting(this, SettingHelper.ACCESSTOKEN)!![0]
            Account.getLimitStatus(object : JsonCallback<LimitStatusModel>() {
                override fun onError(call: Call, e: Exception, id: Int) {
                    if (e.message!!.contains("403")) {
                        val list = LinkedList(Arrays.asList(*SettingHelper.getListSetting(this@SplashActivity, SettingHelper.ACCESSTOKEN)!!))
                        list.removeAt(0)
                        SettingHelper.setListSetting(this@SplashActivity, SettingHelper.ACCESSTOKEN, true, *list.toTypedArray())
                        goLogin()
                    }
                }

                override fun onResponse(response: LimitStatusModel, id: Int) {
                    Account.getUid(object : JsonCallback<String>() {
                        override fun onError(call: Call, e: Exception, id: Int) {
                            goHome()
                        }

                        override fun onResponse(response: String, id: Int) {
                            StaticResource.uid = JsonParser().parse(response).asJsonObject.get("uid").asLong
                            User.getUser(StaticResource.uid, object : JsonCallback<UserModel>() {
                                override fun onError(call: Call, e: Exception, id: Int) {
                                    goHome()
                                }

                                override fun onResponse(response: UserModel, id: Int) {
                                    mUser = response
                                    OkHttpUtils.get().url(response.avatarLarge).build().execute(object : BitmapCallback() {
                                        internal var root: FrameLayout? = null
                                        internal var imgView: ImageView? = null

                                        override fun onError(call: Call, e: Exception, id: Int) {
                                            goHome()
                                        }

                                        override fun onResponse(response: Bitmap, id: Int) {
                                            mFABProgressCircle.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
                                                imgView = v.findViewById(R.id.completeFabIcon) as ImageView
                                                root = v.findViewById(R.id.completeFabRoot) as FrameLayout
                                                if (imgView != null && imgView!!.scaleType != ImageView.ScaleType.CENTER_INSIDE) {
                                                    imgView!!.scaleType = ImageView.ScaleType.CENTER_INSIDE
                                                }
                                            }
                                            mFABProgressCircle.attachListener {
                                                Handler(Looper.getMainLooper()).postDelayed({
                                                    mCircleImageView.setImageBitmap(response)
                                                    TransitionManager.beginDelayedTransition(root)
                                                    root!!.visibility = View.GONE
                                                    navigate()
                                                }, 1000)
                                            }
                                            mFABProgressCircle.beginFinalAnimation()
                                        }
                                    })
                                }
                            })
                        }
                    })
                }
            })
        }
    }

    private fun goLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goHome() {
        val homeIntent = Intent(Intent.ACTION_MAIN)
        homeIntent.addCategory(Intent.CATEGORY_HOME)
        homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(homeIntent)
        finish()
    }

    private fun navigate() {
        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            val transitionAnimation = ActivityOptionsCompat.makeSceneTransitionAnimation(this@SplashActivity, mCircleImageView, getString(R.string.user_profile_icon_name))
            i.putExtra("user", mUser)
            startActivity(i, transitionAnimation.toBundle())
            finish()
        }, 2000)
    }
}
