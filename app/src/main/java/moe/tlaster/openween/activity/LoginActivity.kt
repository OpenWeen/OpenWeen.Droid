package moe.tlaster.openween.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.benny.library.kbinding.view.setContentView
import com.benny.library.kbinding.viewmodel.ViewModel
import moe.tlaster.openween.view.LoginPage
import moe.tlaster.openween.viewmodel.LoginViewModel

class LoginActivity : BaseActivity() {
    override val viewModel: ViewModel = LoginViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginPage().setContentView(this).bindTo(viewModel)
    }
}
