package com.nexaas.challenge.data.core

import org.junit.Test
import java.lang.IllegalArgumentException

class RetrofitFactoryTest {

    @Test
    fun testValidBaseURL() {
        RetrofitFactory.provideRetrofit("https://someurl.com")
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidBaseURL() {
        RetrofitFactory.provideRetrofit("")
    }

}