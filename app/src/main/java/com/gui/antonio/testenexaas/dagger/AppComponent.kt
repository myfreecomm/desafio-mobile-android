package com.gui.antonio.testenexaas.dagger

import android.content.Context
import com.gui.antonio.testenexaas.view.activity.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance activity: MainActivity, @BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)

}