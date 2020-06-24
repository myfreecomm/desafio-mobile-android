package com.example.data.services

import com.example.data.factory.ServiceFactory

class ServiceProviderImpl(
    private val factory: ServiceFactory
) : ServiceProvider {
    override fun getService(): Api {
        return factory.create(Api::class.java)
    }
}