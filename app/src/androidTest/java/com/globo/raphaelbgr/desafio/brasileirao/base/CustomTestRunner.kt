package com.globo.raphaelbgr.desafio.brasileirao.base

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.globo.raphaelbgr.desafio.brasileirao.application.BrasileiraoApplication

class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, BrasileiraoApplication::class.java.name, context)
    }
}