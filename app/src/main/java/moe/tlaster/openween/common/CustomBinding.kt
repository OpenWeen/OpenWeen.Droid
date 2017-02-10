package moe.tlaster.openween.common

import android.widget.TextView
import com.benny.library.kbinding.bind.PropertyBinding
import com.benny.library.kbinding.bind.commandBinding
import com.benny.library.kbinding.common.bindings.textChanges
import com.jakewharton.rxbinding.view.RxView
import com.jakewharton.rxbinding.widget.RxTextView

/**
 * Created by Tlaster on 2017/2/10.
 */
fun TextView.textChanged(path: String) : PropertyBinding = commandBinding(path, RxTextView.afterTextChangeEvents(this).map { it.editable().toString() }, RxView.enabled(this))
