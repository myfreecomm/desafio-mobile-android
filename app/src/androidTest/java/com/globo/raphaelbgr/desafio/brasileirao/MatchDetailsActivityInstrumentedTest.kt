package com.globo.raphaelbgr.desafio.brasileirao

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.globo.raphaelbgr.desafio.brasileirao.base.util.TestUtilInstrumented
import com.globo.raphaelbgr.desafio.brasileirao.matchdetails.MatchDetailsActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MatchDetailsActivityInstrumentedTest {

    @get:Rule
    var mActivityRule: ActivityTestRule<MatchDetailsActivity> =
        ActivityTestRule(MatchDetailsActivity::class.java, true, false)

    @Before
    fun loadMatchDetails() {
        val match = TestUtilInstrumented.loadBrasileiraoMockSingleMatch()
        val intent = Intent()
        intent.putExtra(MatchDetailsActivity.MATCH_PARAM, match)
        mActivityRule.launchActivity(intent)
    }

    @Test
    fun loadMatchDetailsDataCheck() {
        Thread.sleep(1000)
        onView(withText("FLA x VAS")).check(
            matches(isDisplayed())
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
        onView(withText("Maracanã, Rio - RJ")).check(
            matches(isDisplayed())
        )
        onView(withText("LANCE A LANCE")).check(
            matches(isDisplayed())
        )
    }
}
