package moe.tlaster.openween.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.metalab.asyncawait.async
import com.benny.library.kbinding.bind.BindingDelegate
import com.benny.library.kbinding.bind.BindingDisposer
import com.benny.library.kbinding.view.BindingDisposerGenerator

abstract class BaseActivity : AppCompatActivity() , BindingDisposerGenerator, BindingDelegate {
    override val bindingDisposer: BindingDisposer = BindingDisposer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        async.cancelAll()
        bindingDisposer.unbind()
    }
}
