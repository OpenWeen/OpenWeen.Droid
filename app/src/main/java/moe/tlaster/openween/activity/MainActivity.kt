package moe.tlaster.openween.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import com.benny.library.kbinding.bind.BindingDisposer
import com.benny.library.kbinding.viewmodel.ViewModel
import moe.tlaster.openween.R
import moe.tlaster.openween.view.MainPage
import moe.tlaster.openween.viewmodel.MainViewModel
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import com.benny.library.kbinding.view.setContentView


class MainActivity : BaseActivity() {
    override val viewModel: ViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainPage().setContentView(this).bindTo(viewModel)
    }
}
