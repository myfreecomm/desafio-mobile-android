package com.globo.raphaelbgr.desafio.brasileirao

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
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
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection

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
            TestUtilInstrumented.RecyclerViewItemCountAssertion(equalTo(1))
        )
    }

    @Test
    fun loadApiMatchListDataCheck() {
        server.enqueue(MockResponse().setBody(TestUtilInstrumented.loadBrasileiraoRawMockResponse()))
        mActivityRule.launchActivity(Intent())
        Thread.sleep(3000)
        onView(withId(R.id.rv_games_list)).check(
            TestUtilInstrumented.RecyclerViewItemCountAssertion(equalTo(1))
        )
        onView(withText("Flamengo")).check(
            matches(isDisplayed())
        )
        onView(withText("Vasco")).check(
            matches(isDisplayed())
        )
        onView(withText("2")).check(
            matches(isDisplayed())
        )
        onView(withText("0")).check(
            matches(isDisplayed())
        )
        onView(withText("05/02/20 08:40")).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun loadApiMatchListErrorWithNoCacheAndTryAgain() {
        server.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_FOUND))
        server.enqueue(MockResponse().setBody(TestUtilInstrumented.loadBrasileiraoRawMockResponse()))
        mActivityRule.launchActivity(Intent())
        Thread.sleep(3000)
        onView(withText("Não foi possível carregar a lista de jogos, por favor, tente novamente.")).check(
            matches(isDisplayed())
        )
        onView(withText("TENTAR NOVAMENTE")).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.rv_games_list)).check(
            TestUtilInstrumented.RecyclerViewItemCountAssertion(equalTo(1))
        )
    }

    @Test
    fun loadApiMatchEmptyWithNoCacheAndReload() {
        server.enqueue(MockResponse().setBody(TestUtilInstrumented.loadBrasileiraoEmptyRawMockResponse()))
        server.enqueue(MockResponse().setBody(TestUtilInstrumented.loadBrasileiraoRawMockResponse()))
        mActivityRule.launchActivity(Intent())
        Thread.sleep(3000)
        onView(withText("No momento não há jogos para serem carregados, tente novamente mais tarde.")).check(
            matches(isDisplayed())
        )
        onView(withText("OK")).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.rv_games_list)).check(
            TestUtilInstrumented.RecyclerViewItemCountAssertion(equalTo(0))
        )
        onView(withId(R.id.fab_main)).check(
            matches(isDisplayed())
        )
        onView(withId(R.id.fab_main)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.rv_games_list)).check(
            TestUtilInstrumented.RecyclerViewItemCountAssertion(equalTo(1))
        )
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}
