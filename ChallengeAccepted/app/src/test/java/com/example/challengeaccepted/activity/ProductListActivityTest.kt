package com.example.challengeaccepted.activity

import android.os.Build
import com.example.challengeaccepted.MyApp
import com.example.challengeaccepted.base.BaseAutoCloseKoinTest
import com.example.challengeaccepted.feature.product.presentation.list.ProductListActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode


@Config(sdk = [Build.VERSION_CODES.O_MR1], application = MyApp::class)
@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
class ProductListActivityTest : BaseAutoCloseKoinTest() {

    private lateinit var activity: ProductListActivity


    @Before
    fun setUp() {
        activity = buildActivity(ProductListActivity::class.java)
            .create()
            .resume()
            .get()
    }

    @Test
    fun testActivityFound() {
        assertNotNull(activity)
    }

    @Test
    fun testValues() {
        idle(5000)
        assertEquals("$ 36.00", activity.binding.product?.subtotalStr)
        assertEquals("$ 2.00", activity.binding.product?.shippingStr)
        assertEquals("$ 3.24", activity.binding.product?.taxStr)
        assertEquals("$ 41.24", activity.binding.product?.totalStr)
    }

}