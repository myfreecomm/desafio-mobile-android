package com.hotmail.fignunes.desafio_mobile.presentation.common

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class CheckIsOnLineTest {

    private lateinit var applicationContext: Context

    @Before
    fun before() {
        applicationContext = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
    }

    @Test
    fun `should return true when internet on`() {
        val check = CheckIsOnline(applicationContext).execute()
        Assert.assertEquals(true, check)
    }

    @Test
    fun `should return false when internet off`() {
        val check = CheckIsOnline(applicationContext).execute()
        Assert.assertEquals(false, check)
    }

}


