package moe.tlaster.openween.view

import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import com.benny.library.kbinding.dsl.OneWay
import com.benny.library.kbinding.dsl.bind
import com.benny.library.kbinding.view.ViewBinderComponent
import devlight.io.library.ntb.NavigationTabBar
import moe.tlaster.openween.R
import moe.tlaster.openween.activity.LoginActivity
import moe.tlaster.openween.activity.MainActivity
import moe.tlaster.openween.common.extensions.ViewExtension.color
import moe.tlaster.openween.common.extensions.ViewExtension.drawable
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.textInputLayout
import com.benny.library.kbinding.common.bindings.*
import com.benny.library.kbinding.dsl.TwoWay
import com.mcxiaoke.koi.ext.onTextChange
import moe.tlaster.openween.common.nameof
import moe.tlaster.openween.common.textChanged
import moe.tlaster.openween.viewmodel.LoginViewModel

/**
 * Created by Tlaster on 2017/2/9.
 */
class LoginPage : ViewBinderComponent<LoginActivity> {
    override fun builder(): AnkoContext<out LoginActivity>.() -> Unit = {
        linearLayout {
            orientation = LinearLayout.VERTICAL
            scrollView {
                linearLayout {
                    padding = dip(16)
                    orientation = LinearLayout.VERTICAL
                    textView("请输入以下内容完成登陆过程")
                    textInputLayout {
                        editText {
                            hint = "App ID"
                            bind { text(nameof(LoginViewModel::appId), mode = TwoWay) }
                            bind { textChanged(nameof(LoginViewModel::onTextChanged)) }
                        }
                    }
                    textInputLayout {
                        editText {
                            hint = "App Secret"
                            bind { text(nameof(LoginViewModel::appSecret), mode = TwoWay) }
                            bind { textChanged(nameof(LoginViewModel::onTextChanged)) }
                        }
                    }
                    textInputLayout {
                        editText {
                            hint = "Redirect Uri"
                            bind { text(nameof(LoginViewModel::redirectUri), mode = TwoWay) }
                            bind { textChanged(nameof(LoginViewModel::onTextChanged)) }
                        }
                    }
                    textInputLayout {
                        editText {
                            hint = "Scope"
                            bind { text(nameof(LoginViewModel::scope), mode = TwoWay) }
                            bind { textChanged(nameof(LoginViewModel::onTextChanged)) }
                        }
                    }
                    textInputLayout {
                        editText {
                            hint = "Package Name"
                            bind { text(nameof(LoginViewModel::packageName), mode = TwoWay) }
                            bind { textChanged(nameof(LoginViewModel::onTextChanged)) }
                        }
                    }
                }
            }.lparams {
                width = matchParent
                height = dip(0)
                weight = 1f
            }
            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                button("这是什么") {
                    bind { click(nameof(LoginViewModel::what)) }
                }.lparams {
                    width = dip(0)
                    weight = 1f
                    height = matchParent
                }
                button("下一步") {
                    bind { click(nameof(LoginViewModel::login)) }
                }.lparams {
                    width = dip(0)
                    weight = 1f
                    height = matchParent
                }
            }.lparams {
                height = dip(56)
                width = matchParent
            }
        }
    }
}