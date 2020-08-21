package com.example.challengeaccepted.activity

import android.os.Build
import com.example.challengeaccepted.MyApp
import com.example.challengeaccepted.platform.base.BaseActivity
import com.example.challengeaccepted.util.ShadowSnackbar
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@Config(sdk = [Build.VERSION_CODES.O_MR1], application = MyApp::class, shadows = [ShadowSnackbar::class])
@RunWith(RobolectricTestRunner::class)
class BaseActivityTest: AutoCloseKoinTest(){

    private lateinit var activity: BaseActivity

    @Before
    fun setUp() {
      activity = buildActivity(BaseActivityTestImpl::class.java).create().get()
    }

    @Test
    fun testActivityFound() {
      assertNotNull(activity)
    }

//    @Test
//    fun showError() {
//        activity.showErrorMessage("showError Toast Called")
//        assertEquals("showError Toast Called", ShadowSnackbar.getTextOfLatestSnackbar())
//    }
//
//    @Test
//    fun showSuccess() {
//        activity.showSuccessMessage("showSuccess Toast Called")
//        assertEquals("showSuccess Toast Called", ShadowSnackbar.getTextOfLatestSnackbar())
//    }

    private class BaseActivityTestImpl : BaseActivity() {}
}