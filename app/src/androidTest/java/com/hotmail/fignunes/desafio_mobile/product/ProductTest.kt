package com.hotmail.fignunes.desavio_mobile.presentation.movie

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.hotmail.fignunes.desafio_mobile.R
import com.hotmail.fignunes.desafio_mobile.presentation.product.ProductActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ProductTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(ProductActivity::class.java, false, false)

    @Test
    fun callProductDetail() {
        Espresso.onView(ViewMatchers.withId(R.id.productRecyclerview)).perform(ViewActions.click())

        Thread.sleep(2000)

        val getRemoveFromCart = activityTestRule.activity.getString(R.string.remove_from_cart)
        Espresso.onView(ViewMatchers.withId(R.id.buttonRemoveFromCart))
            .check(ViewAssertions.matches(ViewMatchers.withText(getRemoveFromCart)))
    }
}