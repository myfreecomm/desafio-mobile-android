package com.example.challengeaccepted.platform.di

import com.example.challengeaccepted.feature.product.di.productsModules
import com.example.challengeaccepted.platform.di.AppModules.utilsModules
import com.example.challengeaccepted.platform.di.DataModules.apiModules
import com.example.challengeaccepted.platform.di.DataModules.dbModules
import com.example.challengeaccepted.platform.di.DataModules.networkModules
import com.example.challengeaccepted.platform.di.DataModules.serviceModules
import com.example.challengeaccepted.platform.di.DataModules.workerModules
import org.koin.core.module.Module

object AppComponent {

    internal fun getAllModules(): List<Module> =
        listOf(*getAppModules(), *getNetworkModules(), *getServiceModules(), *getDbModules(), *getFeatureModules())

    internal fun getAppModules(): Array<Module> {
        return arrayOf(utilsModules)
    }

    internal fun getServiceModules(): Array<Module> {
        return arrayOf(serviceModules, apiModules, workerModules)
    }

    internal fun getNetworkModules(): Array<Module>  {
        return arrayOf(networkModules)
    }

    internal fun getDbModules(): Array<Module> {
        return arrayOf(dbModules)
    }

    internal fun getFeatureModules(): Array<Module> {
        return arrayOf(productsModules)
    }

}