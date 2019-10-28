package com.nexaas.challenge.data.remote.service

import com.nexaas.challenge.data.core.RetrofitFactory
import com.nexaas.challenge.data.core.exceptions.InvalidApiBaseUrl

internal object ApiServicesFactory {

    private var apiBaseUrl: String? = null
    private var apiServices: ApiServices? = null

    /**
     * Changes the API base url
     */
    fun with(apiBaseUrl: String): ApiServicesFactory {
        if (apiBaseUrl.isEmpty())
            throw InvalidApiBaseUrl("The API base url can't be empty.")

        if (apiBaseUrl == this. apiBaseUrl)
            return ApiServicesFactory

        this.apiBaseUrl = apiBaseUrl

        // Invalidate previously instantiated services
        apiServices = null

        return ApiServicesFactory
    }

    /**
     * Gets the API Services instanced by Retrofit.
     */
    fun getApiServices(): ApiServices {
        if (apiBaseUrl.isNullOrEmpty())
            throw InvalidApiBaseUrl("The API base url isn't defined yet.")

        apiServices = this.apiServices ?: RetrofitFactory.provideRetrofit(apiBaseUrl!!).create(ApiServices::class.java)
        return apiServices!!
    }

}