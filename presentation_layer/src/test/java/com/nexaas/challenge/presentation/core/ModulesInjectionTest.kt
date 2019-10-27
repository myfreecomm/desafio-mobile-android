package com.nexaas.challenge.presentation.core

import android.app.Activity
import com.nexaas.challenge.presentation.view.list.ProductsListPresenter
import com.nexaas.challenge.presentation.view.product.ProductPresenter
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.parameter.parametersOf
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ModulesInjectionTest: KoinTest {

    @Mock
    lateinit var mockActivity: Activity

    @Mock
    private lateinit var mockAndroidContext: NexaasChallengeApp

    private val overrideNativeModule = module(override = true) {

    }

    @Test
    fun checkModules() {
        koinApplication {
            androidContext(mockAndroidContext)
            modules(listOf(commonModule, dataModule, domainModule, presentationModule, overrideNativeModule))
        }.checkModules {
            // Presenters Test
            create<ProductsListPresenter> { parametersOf(mockActivity) }
            create<ProductPresenter> { parametersOf(mockActivity) }
        }
    }

}