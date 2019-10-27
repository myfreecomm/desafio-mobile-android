package com.nexaas.challenge.data.remote.service

import com.nexaas.challenge.data.core.exceptions.InvalidApiBaseUrl
import org.junit.Test

class ApiServicesFactoryTest {

    @Test(expected = InvalidApiBaseUrl::class)
    fun testNotInstanced() {
        ApiServicesFactory.getApiServices()
    }

    @Test
    fun testSuccess() {
        val factory = ApiServicesFactory.with("https://validurl.com")
        factory.getApiServices()
    }

    @Test(expected = InvalidApiBaseUrl::class)
    fun testEmptyUrl() {
        ApiServicesFactory.with("").getApiServices()
    }

}