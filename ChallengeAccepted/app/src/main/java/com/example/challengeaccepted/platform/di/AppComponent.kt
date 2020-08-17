package com.example.challengeaccepted.platform.di

import com.example.challengeaccepted.feature.product.di.productsModules
import com.example.challengeaccepted.platform.di.AppModules.utilsModules
import com.example.challengeaccepted.platform.di.DataModules.apiModules
import com.example.challengeaccepted.platform.di.DataModules.networkModules
import com.example.challengeaccepted.platform.di.DataModules.serviceModules
import org.koin.core.module.Module

object AppComponent {

    internal fun getAllModules(): List<Module> =
        listOf(*getAppModules(), *getNetworkModules(), *getServiceModules(), *getFeatureModules())

    internal fun getAppModules(): Array<Module> {
        return arrayOf(utilsModules)
    }

    internal fun getServiceModules(): Array<Module> {
        return arrayOf(serviceModules, apiModules)
    }

    internal fun getNetworkModules(): Array<Module>  {
        return arrayOf(networkModules)
    }


    internal fun getFeatureModules(): Array<Module> {
        return arrayOf(productsModules)
    }

}