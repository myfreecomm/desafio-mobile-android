package br.com.brunocardoso.studying.desafioandroidnexaasbruno.di

import android.os.Environment
import android.view.View
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.BuildConfig
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.model.Product
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.repository.ShoppingCartRepository
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.repository.ShoppingCartRepositoryImpl
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.service.ShoppingCartService
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.ui.shoppingcart.ShoppingCartAdapter
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.ui.shoppingcart.ShoppingCartViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

val networkModule = module {
    factory { provideHttpLoggingInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideShoppingCartService(get()) }
    single { provideRetrofit(get()) }
}

val viewModelModule = module {
    viewModel {
        ShoppingCartViewModel(
            ShoppingCartRepository(get())
        )
    }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val httpCacheDirectory = File(getCacheDir(), "offlineCache")
    val cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)
    return OkHttpClient().newBuilder().cache(cache)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}

fun provideShoppingCartService(retrofit: Retrofit): ShoppingCartService =
    retrofit.create(ShoppingCartService::class.java)

fun getCacheDir(): File {
    return File("${Environment.getExternalStorageState()}/cache")
}

val viewModelModuleTest = module {
    viewModel { (repo: ShoppingCartRepositoryImpl) ->
        ShoppingCartViewModel(repo)
    }
}

val adapterModule = module {
    single { (data: List<Product>, onItemClickListener: ((product: Product, view: View) -> Unit)) ->
        ShoppingCartAdapter(
            data, onItemClickListener
        )
    }
}