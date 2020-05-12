package com.challenge.raphaelbgr.desafio.brasileirao.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.challenge.raphaelbgr.desafio.brasileirao.application.BrasileiraoApplication
import com.challenge.raphaelbgr.desafio.brasileirao.application.di.ApplicationComponent
import com.challenge.raphaelbgr.desafio.brasileirao.base.di.BaseActivityModule
import com.challenge.raphaelbgr.desafio.brasileirao.base.di.DaggerBaseActivityComponent
import com.challenge.raphaelbgr.desafio.brasileirao.util.NavigatorUtil
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorUtil: NavigatorUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        DaggerBaseActivityComponent.builder()
            .baseActivityModule(BaseActivityModule(this))
            .build()
            .inject(this)
    }

    /**
     * Get the Main Application component for dependency injection.
     */
    protected open fun getApplicationComponent(): ApplicationComponent {
        return BrasileiraoApplication.getApplicationComponent((application as BrasileiraoApplication))
    }
}
