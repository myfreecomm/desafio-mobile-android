package com.nexaas.challenge.data.remote.service

import com.nexaas.challenge.data.core.RequestManager

internal class ApiServicesFactory(private val requestManager: RequestManager,
                                  private var apiBaseUrl: String = "https://raw.githubusercontent.com/") {

    private var apiServices: ApiServices? = null

    /**
     * Gets the API Services instanced by Retrofit.
     */
    fun getApiServices(): ApiServices {
        apiServices = this.apiServices ?: requestManager.provideRetrofit(apiBaseUrl).create(ApiServices::class.java)
        return apiServices!!
    }

    /**
     * Changes the default baseURL
     */
    fun with(apiBaseUrl: String): ApiServicesFactory {
        this.apiBaseUrl = apiBaseUrl
        apiServices = this.apiServices ?: requestManager.provideRetrofit(apiBaseUrl).create(ApiServices::class.java)
        return this
    }

}