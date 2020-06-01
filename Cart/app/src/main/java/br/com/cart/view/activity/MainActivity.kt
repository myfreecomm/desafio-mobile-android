package br.com.cart.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cart.R
import br.com.cart.model.Product
import br.com.cart.utils.CartHelper
import br.com.cart.view.adapter.CartAdapter
import br.com.cart.view.adapter.RecyclerItemClickListener
import br.com.cart.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    companion object {
        private const val PRODUCT_EXTRA = "product"
    }

    private val cartViewModel: CartViewModel by viewModel()

    private val cartAdapter: CartAdapter by lazy{ CartAdapter() }

    private var productList = listOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = cartAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        recycler_view.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        recycler_view.addOnItemTouchListener(RecyclerItemClickListener(this, recycler_view, object: RecyclerItemClickListener.OnItemClickListener{
            override fun onItemClick(view: View?, position: Int) {
                //Call detail activity with transition
                val iv = view!!.findViewById(R.id.iv_product) as View
                val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, iv, ViewCompat.getTransitionName(iv).toString())
                startActivity(Intent(applicationContext, DetailsActivity::class.java).putExtra(PRODUCT_EXTRA,  productList[position]), options.toBundle())
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onLongItemClick(view: View?, position: Int) {

            }

        }))

        getProducts()

    }

    private fun getProducts(){
        cartViewModel.getProducts(object: Callback<List<Product>>{
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                response.let {
                    productList = response.body()!!
                    cartAdapter.add(productList)
                    sunValues(productList)
                }
            }

        })
    }

    fun sunValues(productList: List<Product>){
        val total: Double; var subtotal = 0.0; var shipping = 0.0; var tax = 0.0
        for (item in productList){
            subtotal += item.price
            shipping += item.shipping
            tax += item.tax
        }
        total = subtotal + shipping + tax

        val cartHelper = CartHelper()
        tv_total.text = cartHelper.formatValue(total)
        tv_subtotal.text = cartHelper.formatValue(subtotal)
        tv_shipping.text = cartHelper.formatValue(shipping)
        tv_tax.text = cartHelper.formatValue(tax)

        tv_quantity_in_cart.text = "${cartAdapter.itemCount} items in your cart"

    }

}
