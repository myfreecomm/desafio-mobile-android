package br.com.mob9.cart.core.di

import android.app.Application
import br.com.mob9.cart.core.base.BaseApplication
import br.com.mob9.cart.core.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuilderModule::class,
    ViewModelFactoryModule::class,
    AppModule::class,
    NetworkModule::class,
    DatabaseModule::class
])
interface AppComponent: AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): AppComponent
    }
}