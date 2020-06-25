package com.gui.antonio.testenexaas.dagger

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.gui.antonio.testenexaas.database.AppDatabase
import com.gui.antonio.testenexaas.database.ProductDao
import com.gui.antonio.testenexaas.model.ProductModel
import com.gui.antonio.testenexaas.repository.ProductRepository
import com.gui.antonio.testenexaas.view.activity.MainActivity
import com.gui.antonio.testenexaas.viewmodel.MainViewModel
import com.gui.antonio.testenexaas.viewmodel.MainViewModelFactory
import dagger.BindsInstance
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideRepository(productModel: ProductModel): ProductRepository {
        return ProductRepository(productModel)
    }

    @Provides
    fun provideModel(applicationContext: Context): ProductModel {
        return ProductModel(provideDAO(applicationContext))
    }

    @Provides
    fun provideDB(applicationContext: Context) : AppDatabase{
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, "dbproduct").build()
    }

    @Provides
    fun provideDAO(applicationContext: Context): ProductDao {
        return provideDB(applicationContext).productDao()
    }

    @Provides
    fun provideViewModel(activity: MainActivity, applicationContext: Context): MainViewModel {
        return ViewModelProvider(activity, MainViewModelFactory(provideRepository(provideModel(applicationContext)))).get(MainViewModel::class.java)
    }

}