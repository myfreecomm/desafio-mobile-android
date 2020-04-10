package com.globo.raphaelbgr.desafio.brasileirao

import android.content.Intent
import android.os.Bundle
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.globo.raphaelbgr.desafio.brasileirao.base.BaseActivityTest
import com.globo.raphaelbgr.desafio.brasileirao.main.MainActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest: BaseActivityTest() {

    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, true, false)

    private lateinit var server: MockWebServer

    @Before
    fun setUpWebServer() {
        server = MockWebServer()
        server.url("/")
        InstrumentationRegistry.registerInstance(
            InstrumentationRegistry.getInstrumentation(),
            Bundle()
        )
    }

    @Test
    fun loadApiMatchListSuccess() {
        server.enqueue(MockResponse().setBody(TestUtilInstrumented.loadBrasileiraoRawMockResponse()))
        mActivityRule.launchActivity(Intent())
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}
