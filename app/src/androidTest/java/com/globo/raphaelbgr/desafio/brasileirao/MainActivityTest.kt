package com.globo.raphaelbgr.desafio.brasileirao

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.globo.raphaelbgr.desafio.brasileirao.base.BaseActivityTest
import com.globo.raphaelbgr.desafio.brasileirao.main.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MainActivityTest: BaseActivityTest() {

    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.globo.raphaelbgr.desafio.brasileirao", appContext.packageName)
    }
}
