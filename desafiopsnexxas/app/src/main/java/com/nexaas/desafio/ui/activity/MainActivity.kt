package com.nexaas.desafio.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexaas.desafio.R
import com.nexaas.desafio.model.Product
import com.nexaas.desafio.ui.adapter.ProductListAdapter
import com.nexaas.desafio.viewmodel.ProductViewModel
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by viewModel()
    private val picasso: Picasso by inject()
    val subscriptions = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        subscriptions.add(productViewModel?.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                configureList(it)
                progressBar.visibility = View.INVISIBLE
            }, {
                progressBar.visibility = View.INVISIBLE
                showError(it.message.toString())
            }))
    }


    private fun loadList() {
        productViewModel.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                configureList(it)
                progressBar.visibility = View.INVISIBLE
            }, {

            })
    }

    private fun configureList(list: List<Product>){
        val adapter = ProductListAdapter(picasso, list , this::openActivity)
        recyclerlist.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }
    }

    fun openActivity(product: Product) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }

    private fun showError(msg:String) {
        Toast.makeText(this, "Erro encontrado: $msg", Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()
        subscriptions.clear()
    }
}
