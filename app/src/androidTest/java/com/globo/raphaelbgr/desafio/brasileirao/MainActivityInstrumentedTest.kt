package com.globo.raphaelbgr.desafio.brasileirao

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.globo.raphaelbgr.desafio.brasileirao.application.BrasileiraoApplication
import com.globo.raphaelbgr.desafio.brasileirao.application.di.DaggerApplicationComponent
import com.globo.raphaelbgr.desafio.brasileirao.base.util.TestUtilInstrumented
import com.globo.raphaelbgr.desafio.brasileirao.di.TestApplicationModule
import com.globo.raphaelbgr.desafio.brasileirao.di.TestDataModule
import com.globo.raphaelbgr.desafio.brasileirao.main.MainActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityInstrumentedTest {

    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, true, false)

    private lateinit var server: MockWebServer

    @Before
    fun setUpWebServer() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as BrasileiraoApplication

        server = MockWebServer()
        val path = "match_list"
        val baseUrl = server.url("/")

        val testComponent = DaggerApplicationComponent.builder()
            .applicationModule(TestApplicationModule(app))
            .dataModule(TestDataModule(baseUrl.toString().replace(path, "")))
            .build()

        app.applicationComponent = testComponent
        testComponent.inject(app)
    }

    @Test
    fun loadApiMatchListSuccess() {
        server.enqueue(MockResponse().setBody(TestUtilInstrumented.loadBrasileiraoRawMockResponse()))
        mActivityRule.launchActivity(Intent())
        Thread.sleep(3000)
        onView(withId(R.id.rv_games_list)).check(
            RecyclerViewItemCountAssertion(equalTo(1))
        )
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    internal class RecyclerViewItemCountAssertion(private val matcher: Matcher<Int>) :
        ViewAssertion {
        override fun check(
            view: View,
            noViewFoundException: NoMatchingViewException?
        ) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            MatcherAssert.assertThat(adapter!!.itemCount, matcher)
        }
    }
}
