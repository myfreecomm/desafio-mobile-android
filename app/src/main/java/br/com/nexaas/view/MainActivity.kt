package br.com.nexaas.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.nexaas.R
import br.com.nexaas.adapter.CartRecyclerAdapter
import br.com.nexaas.interactor.LoadItemsInteractorImpl
import br.com.nexaas.model.Cart
import br.com.nexaas.presenter.MainPresenterImpl
import br.com.nexaas.presenter.interfaces.MainPresenter
import br.com.nexaas.view.interfaces.MainView
import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView, AdapterView.OnItemClickListener {

    private lateinit var presenter: MainPresenter
    private lateinit var cartAdapter: CartRecyclerAdapter

    private var mSubTotal = 0.0
    private var mTotal = 0.0

    @BindView(R.id.list_cart)
    lateinit var cartRecyclerView: RecyclerView

    @BindView(R.id.progress)
    lateinit var progressBar: ProgressBar

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.imeOptions = EditorInfo.IME_ACTION_DONE

            searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                    presenter.fetchCart()
                    return true
                }
            })

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    cartAdapter.filter.filter(newText)
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        cartRecyclerView.visibility = View.INVISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
        cartRecyclerView.visibility = View.VISIBLE
    }

    override fun populateRecyclerCart(items: ArrayList<Cart>) {
        loadRecyclerCart(items)
        populateCheckoutItems()
    }

    private fun populateCheckoutItems() {
        text_qty_item_cart.text =
            cartAdapter.itemCount.toString() + " " + getString(R.string.item_qty_text)
        text_subtotal_value.text = mSubTotal.toString()
        text_total_value.text = mTotal.toString()
    }

    private fun loadRecyclerCart(items: ArrayList<Cart>) {

        cartRecyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 1)
            cartAdapter = CartRecyclerAdapter()
            cartAdapter.submitList(items)
            adapter = cartAdapter

            for (item in items) {
                mSubTotal += (item.price.toDouble() / 100)
            }

            mTotal = mSubTotal + (text_shipping_value.text.toString()
                .toDouble()) + (text_tax_value.text.toString().toDouble())

            cartAdapter.onItemClick = { cartRepo ->
                val intent = newIntent(this@MainActivity, cartRepo)
                startActivity(intent)
            }

        }
    }


    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar

        actionBar!!.setDisplayHomeAsUpEnabled(false)

        actionBar.title = MainApplication.applicationContext().getString(R.string.app_name)
        actionBar.subtitle = MainApplication.applicationContext().getString(R.string.title_card)
        actionBar.elevation = 4.0F

        ButterKnife.bind(this)

        presenter = MainPresenterImpl(this, LoadItemsInteractorImpl())
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        presenter.onItemClicked(position)
    }

    override fun onResume() {
        presenter.onResume()
        resetCheckoutValues()
        super.onResume()
    }

    private fun resetCheckoutValues() {
        mSubTotal = 0.0
        mTotal = 0.0
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    companion object {

        private val INTENT_CART = "cart_extra"

        fun newIntent(context: Context, cart: Cart): Intent {
            val intent = Intent(context, CartDetailsActivity::class.java)
            intent.putExtra(INTENT_CART, cart)
            return intent
        }
    }
}
