package br.com.nexaas.common.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    protected val binding: T by lazy {
        setContentView<T>(this, getLayoutRes())
            .apply {
                lifecycleOwner = this@BaseActivity
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView(savedInstanceState = savedInstanceState)
    }
}