package com.valderas.leonardo.nexaasdesafio

import android.content.Intent
import android.provider.SyncStateContract
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.CoreMatchers.*
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.times
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.valderas.leonardo.nexaasdesafio.main.mvvm.view.main.MainActivity
import com.valderas.leonardo.nexaasdesafio.main.mvvm.view.main.MainAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.valderas.leonardo.nexaasdesafio.main.mvvm.view.pull.PullRequestActivity
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import junit.framework.AssertionFailedError
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    val visible = ViewMatchers.Visibility.VISIBLE
    val gone = ViewMatchers.Visibility.GONE

    @Test
    fun a_validateToolbarTitle_shouldBeAppName() {
        val title: CharSequence = InstrumentationRegistry
                .getTargetContext().getString(R.string.app_name)
        matchToolbarTitle(title)
    }

    @Test
    fun b_validateVisibilityRecyclerAndLoading() {
        onView(withId(R.id.pb_loading)).check(matches((withEffectiveVisibility(visible))))
        onView(withId(R.id.recycler_view)).check(matches((withEffectiveVisibility(visible))))
        waitThread()
        onView(withId(R.id.pb_loading)).check(matches((withEffectiveVisibility(gone))))
        onView(withId(R.id.recycler_view)).check(matches((withEffectiveVisibility(visible))))
    }

    @Test
    fun c_validateOnClickRecyclerViewItemPosition_ShouldGoToPullRequestActivity() {

        Intents.init()

        waitThread()
        onView(ViewMatchers.withId(R.id.recycler_view))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<MainAdapter.ViewHolder>(0, ViewActions.click()))
        activityRule.launchActivity(Intent())

        intended(hasComponent(MainActivity::class.java.name))
        intended(hasComponent(PullRequestActivity::class.java.name), times(1))

        Intents.release()
    }

    @Test
    fun d_validateAdapterSize_shouldBeThirty() {
        waitThread()
        val adapter = activityRule.activity.recyclerAdapter
        onView(withId(R.id.recycler_view)).check(matches((withEffectiveVisibility(visible))))
        assertThat(adapter.itemCount, `is`(equalTo(30)))
    }

    private fun waitThread() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun matchToolbarTitle(title: CharSequence): ViewInteraction {
        return onView(
                allOf(isAssignableFrom(TextView::class.java),
                        withParent(isAssignableFrom(Toolbar::class.java))))
                .check(matches(withText(title.toString())))
    }
}