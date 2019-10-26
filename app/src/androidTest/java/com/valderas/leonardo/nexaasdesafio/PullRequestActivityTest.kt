package com.valderas.leonardo.nexaasdesafio

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.CoreMatchers.*
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.valderas.leonardo.nexaasdesafio.main.mvvm.view.pull.PullRequestActivity
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.intent.matcher.IntentMatchers.*
import com.valderas.leonardo.nexaasdesafio.main.mvvm.view.pull.PullRequestAdapter
import com.valderas.leonardo.nexaasdesafio.main.utils.Helpers


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class PullRequestActivityTest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<PullRequestActivity> = object : ActivityTestRule<PullRequestActivity>(PullRequestActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, PullRequestActivity::class.java)
            result.putExtra(Helpers.NAME_EXTRA, "java-design-patterns")
            result.putExtra(Helpers.OWNER_EXTRA, "iluwatar")
            return result
        }
    }

    val visible = ViewMatchers.Visibility.VISIBLE
    val gone = ViewMatchers.Visibility.GONE

    @Test
    fun b_validateVisibilityComponentInit() {
        onView(withId(R.id.pb_loading)).check(matches((withEffectiveVisibility(visible))))
        onView(withId(R.id.recycler_view)).check(matches((withEffectiveVisibility(visible))))
        waitThread()
        onView(withId(R.id.pb_loading)).check(matches((withEffectiveVisibility(gone))))
        onView(withId(R.id.recycler_view)).check(matches((withEffectiveVisibility(visible))))
    }

    @Test
    fun c_validateOnClickRecyclerViewItemPosition_ShouldOpenBrowser() {
        waitThread()

        Intents.init()
        onView(ViewMatchers.withId(R.id.recycler_view))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<PullRequestAdapter.ViewHolder>(0, ViewActions.click()))
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_VIEW)),
                toPackage("com.android.browser")))
        Intents.release()
    }

    @Test
    fun d_validateAdapterSize_shouldBeMoreThanZero() {
        waitThread()
        val adapter = activityRule.activity.recyclerAdapter
        onView(withId(R.id.recycler_view)).check(matches((withEffectiveVisibility(visible))))
        assertThat(adapter.itemCount, `not`(equalTo(0)))
    }

    private fun waitThread() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}