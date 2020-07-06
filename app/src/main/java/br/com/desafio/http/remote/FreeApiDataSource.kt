package br.com.desafio.http.remote

import br.com.desafio.ui.model.Item
import br.com.desafio.http.ItemDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FreeApiDataSource(val freeApi: FreeApi): ItemDataSource {

    override fun listAllObjects(success: (List<Item>) -> Unit, failure: () -> Unit) {
        val call = freeApi.listObjects()
        call.enqueue(object : Callback<List<Item>>{
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if (response.isSuccessful) {
                    val itens = mutableListOf<Item>()
                    response.body()?.forEach {
                        itens.add(it)
                    }
                    success(itens)
                } else {
                    failure()
                }
            }
            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                failure()
            }
        })
    }
}