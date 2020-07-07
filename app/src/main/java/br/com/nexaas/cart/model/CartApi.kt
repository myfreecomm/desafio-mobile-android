package br.com.nexaas.cart.model

import android.net.Uri
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

class CartApi {
    val service: APIdef

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/api/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()

        service = retrofit.create<APIdef>(APIdef::class.java)
    }

    fun loadItems(): Observable<CartItem>? {
        return service.listCartItems()
            .flatMap { itemResults -> Observable.from(itemResults) }
            .map { item ->
                CartItem(
                    item.name, item.quantity, item.stock, item.image_url, item.price, item.tax,
                    item.shipping, item.description
                )
            }
    }
}