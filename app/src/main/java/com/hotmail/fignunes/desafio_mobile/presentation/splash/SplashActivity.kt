package com.hotmail.fignunes.desafio_mobile.presentation.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hotmail.fignunes.desafio_mobile.R
import com.hotmail.fignunes.desafio_mobile.common.BaseActivity
import com.hotmail.fignunes.desafio_mobile.databinding.ActivitySplashBinding
import com.hotmail.fignunes.desafio_mobile.presentation.product.ProductActivity
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SplashActivity : BaseActivity(), SplashContract {

    private val presenter: SplashPresenter by inject { parametersOf(this) }
    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.presenter = presenter
        presenter.onCreate()
    }

    override fun displayProducts() {
        startActivity<ProductActivity>()
        finish()
    }
}
