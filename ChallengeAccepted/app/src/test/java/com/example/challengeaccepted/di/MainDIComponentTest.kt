package com.example.challengeaccepted.di

import com.example.challengeaccepted.platform.di.AppComponent

fun configureTestAppComponentMocked(baseApi: String) =
    AppComponent.getServiceModules().toMutableList().apply {
        add(MockWebServerDIPTest)
        add(configureNetworkModuleForTest(baseApi))
    }

fun configureTestAppComponent() =
    AppComponent.getServiceModules().toMutableList().apply {
        add(configureNetworkModuleForTest("https://raw.githubusercontent.com"))
    }

