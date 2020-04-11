package com.globo.raphaelbgr.desafio.brasileirao

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.globo.raphaelbgr.desafio.brasileirao.base.util.TestUtilInstrumented
import com.globo.raphaelbgr.desafio.brasileirao.matchdetails.MatchDetailsActivity
import com.globo.raphaelbgr.desafio.brasileirao.matchhighlights.MatchHighlightsActivity
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MatchHighlightsActivityInstrumentedTest {

    @get:Rule
    var mActivityRule: ActivityTestRule<MatchHighlightsActivity> =
        ActivityTestRule(MatchHighlightsActivity::class.java, true, false)

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
        onView(withText("FLA x VAS - Lance a lance")).check(
            matches(isDisplayed())
        )
        onView(withText("Início de partida")).check(
            matches(isDisplayed())
        )
        onView(withText("00m 00s")).check(
            matches(isDisplayed())
        )
        onView(withText("Gol de Gabriel Barbosa")).check(
            matches(isDisplayed())
        )
        onView(withText("35m 17s")).check(
            matches(isDisplayed())
        )
        onView(ViewMatchers.withId(R.id.rv_games_list)).check(
            TestUtilInstrumented.RecyclerViewItemCountAssertion(Matchers.equalTo(6))
        )
    }
}
