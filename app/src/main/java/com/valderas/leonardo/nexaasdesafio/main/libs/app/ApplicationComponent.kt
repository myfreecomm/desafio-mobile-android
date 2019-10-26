package com.valderas.leonardo.nexaasdesafio.main.libs.app

import com.valderas.leonardo.nexaasdesafio.Application
import com.valderas.leonardo.nexaasdesafio.main.libs.di.activity.ActivityViewModelBuilder
import com.valderas.leonardo.nexaasdesafio.main.libs.di.api.ApiServiceModule
import com.valderas.leonardo.nexaasdesafio.main.libs.di.api.RepoServiceModule
import com.valderas.leonardo.nexaasdesafio.main.libs.di.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
                      ApplicationModule::class,
                      ApiServiceModule::class,
                      RepoServiceModule::class,
                      ViewModelModule::class,
                      ActivityViewModelBuilder::class])
interface ApplicationComponent : AndroidInjector<Application> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<Application>()
}