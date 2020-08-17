package com.example.challengeaccepted.activity

import android.os.Build
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeaccepted.MyApp
import com.example.challengeaccepted.R
import com.example.challengeaccepted.base.BaseAutoCloseKoinTest
import com.example.challengeaccepted.base.BaseKoinTest
import com.example.challengeaccepted.di.configureTestAppComponent
import com.example.challengeaccepted.feature.product.domain.mapper.ProductDataMapper
import com.example.challengeaccepted.feature.product.presentation.list.presentation.ProductListActivity
import com.example.challengeaccepted.feature.product.presentation.list.presentation.ProductListViewModel
import com.example.challengeaccepted.platform.base.BaseAdapter
import kotlinx.android.synthetic.main.activity_product_list.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.context.startKoin
import org.robolectric.Robolectric
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import org.robolectric.shadows.ShadowLooper
import java.net.HttpURLConnection


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