package com.globo.raphaelbgr.desafio.brasileirao.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.globo.raphaelbgr.desafio.brasileirao.application.di.ApplicationModule
import com.globo.raphaelbgr.desafio.brasileirao.application.di.DaggerApplicationComponent
import com.globo.raphaelbgr.desafio.brasileirao.util.NavigatorUtil
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorUtil: NavigatorUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
            .inject(this)
    }
}
