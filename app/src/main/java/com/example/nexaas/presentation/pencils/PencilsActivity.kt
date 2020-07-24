package com.example.nexaas.presentation.pencils

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nexaas.R
import com.example.nexaas.presentation.base.BaseActivity
import com.example.nexaas.presentation.details.PencilDetailsActivity
import kotlinx.android.synthetic.main.activity_pencils.*


class PencilsActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pencils)

        setupToolBar(toolBarMain, R.string.cart_title)

        val viewModel: PencilsViewModel = ViewModelProviders.of(this).get(PencilsViewModel::class.java)

        viewModel.pencilsLiveData.observe(this, Observer {
            it?.let { pencils ->
                with(recyclePencils){
                    layoutManager = LinearLayoutManager(this@PencilsActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    addItemDecoration(DividerItemDecoration(this@PencilsActivity, LinearLayoutManager.VERTICAL))
                    adapter = PencilsAdapter(pencils) {pencil ->
                        val intent = PencilDetailsActivity.getStartIntent(this@PencilsActivity, pencil.imageUrl,
                                pencil.item, pencil.stock.toInt(), pencil.price, pencil.description)
                        this@PencilsActivity.startActivity(intent)
                    }
                }

            }
        })

        viewModel.pencilsCartLiveData.observe(this, Observer {
            it?.let { pencilsQuantity ->
                var quantity = getString(R.string.cart_quantity)
                quantity = quantity.replace("#", pencilsQuantity.toString())
                cartQuantity.text = quantity
            }
        })

        viewModel.totalLiveData.observe(this, Observer {
            it?.let { it ->
                total.text  = "$ $it"
            }
        })

        viewModel.subTotalLiveData.observe(this, Observer {
            it?.let { it ->
                subTotal.text = "$ $it"
            }
        })

        viewModel.shippinglLiveData.observe(this, Observer {
            it?.let { it ->
                shipping.text = "$ $it"
            }
        })

        viewModel.taxlLiveData.observe(this, Observer {
            it?.let { it ->
                tax.text = "$ $it"
            }
        })

        viewModel.getPencils()
    }
}