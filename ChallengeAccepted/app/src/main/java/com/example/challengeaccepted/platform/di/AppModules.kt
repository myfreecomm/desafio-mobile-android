package com.example.challengeaccepted.platform.di

import com.example.challengeaccepted.platform.util.GsonHelper
import org.koin.dsl.module

object AppModules {

    internal val utilsModules = module {
        factory { GsonHelper().gson }
    }

}