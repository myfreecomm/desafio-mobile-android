package com.globo.raphaelbgr.desafio.brasileirao.base

import androidx.test.platform.app.InstrumentationRegistry
import com.globo.raphaelbgr.desafio.brasileirao.application.di.DaggerApplicationComponent
import com.globo.raphaelbgr.desafio.brasileirao.di.TestApplicationModule
import org.junit.Before

open class BaseActivityTest {

    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as TestBrasileiraoApplication

        DaggerApplicationComponent
            .builder()
            .applicationModule(TestApplicationModule(app))
            .build()
            .inject(app)
    }
}
