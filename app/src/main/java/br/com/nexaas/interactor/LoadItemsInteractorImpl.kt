package br.com.nexaas.interactor

import android.util.Log
import br.com.nexaas.interactor.interfaces.LoadItemsInteractor
import br.com.nexaas.model.Cart
import br.com.nexaas.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class LoadItemsInteractorImpl : LoadItemsInteractor {

    private val TAG = "LoadItemsInteractorImpl"

    override fun fetchCart(onFinishedListener: LoadItemsInteractor.OnFinishedListener) {

        val call = ApiClient().apiService().fetchCart()
        call.enqueue(object : Callback<ArrayList<Cart>> {
            
            override fun onFailure(call: Call<ArrayList<Cart>>, t: Throwable) {
                Log.e(TAG, t.toString())
                onFinishedListener.onFailure(t)
            }

            override fun onResponse(
                call: Call<ArrayList<Cart>>,
                response: Response<ArrayList<Cart>>
            ) {
                val carts = if (response.body() != null) response.body() else null
                Log.d(TAG, "Count Cart " + carts!!.size)
                onFinishedListener.onFinished(carts)
            }
        })
    }
}
