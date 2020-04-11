package com.globo.raphaelbgr.desafio.brasileirao.base

import com.globo.raphaelbgr.desafio.brasileirao.application.di.CoroutineModule
import com.globo.raphaelbgr.desafio.brasileirao.base.di.DaggerBasePresenterComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

abstract class BasePresenter() {

    @Inject
    lateinit var coroutineScope: CoroutineScope

    init {
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        DaggerBasePresenterComponent.builder()
            .coroutineModule(CoroutineModule())
            .build()
            .inject(this)
    }
}