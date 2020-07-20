package br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.repository

import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.ApiService
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.model.Product
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.result.ShoppingCartResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShoppingCartRepository : ShoppingCartRepositoryImpl {

    override suspend fun getProducts(resultCallback: (result: ShoppingCartResult) -> Unit) {
        withContext(Dispatchers.Default) {
            ApiService.shoppingCartService.getProducts().enqueue(object :
                Callback<List<Product>> {
                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {
                    when {
                        response.isSuccessful -> {
                            response.body()?.let {
                                resultCallback(ShoppingCartResult.Success(it))
                            }
                        }
                        else -> resultCallback(ShoppingCartResult.ApiError(response.code()))
                    }
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    resultCallback(ShoppingCartResult.ServerError)
                }

            })
        }
    }
}