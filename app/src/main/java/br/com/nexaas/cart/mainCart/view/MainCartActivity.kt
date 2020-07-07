package br.com.nexaas.cart.mainCart.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.nexaas.cart.R
import br.com.nexaas.cart.mainCart.adapter.CartItemsAdapter
import br.com.nexaas.cart.mainCart.interactor.MainCartInteractor
import br.com.nexaas.cart.mainCart.interactor.MainCartInteractorContract
import br.com.nexaas.cart.mainCart.model.CartViewModel
import br.com.nexaas.cart.mainCart.presenter.MainCartPresenter
import br.com.nexaas.cart.model.CartItem
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_main_cart.*

class MainCartActivity : AppCompatActivity(), MainCartActivityContract,
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    private lateinit var interactor: MainCartInteractorContract
    private lateinit var cartItemsAdapter: CartItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_cart)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        initInteractor()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun initInteractor() {
        interactor = MainCartInteractor(MainCartPresenter(this))
        interactor.initComponents()
    }

    override fun initComponents() {
        interactor.getDataApi()
    }

    override fun getValuesCart(cartItems: MutableList<CartItem>) {
        interactor.getValuesCart(cartItems)
    }

    override fun setValuesCart(cartViewModel: CartViewModel) {
        tvCountItems.text = String.format(resources.getString(R.string.txt_count_items_cart),
            cartViewModel.countItems.toString())

        cartItemsAdapter = CartItemsAdapter(this, cartViewModel.cartItems)
        rvCartItems.adapter = cartItemsAdapter
        rvCartItems.setHasFixedSize(true)
        rvCartItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
            false)

        tvTotal.text = ("$" + cartViewModel.total).trimIndent()
        tvSubTotal.text = ("$" + cartViewModel.subTotal).trimIndent()
        tvShipping.text = ("$" + cartViewModel.shipping).trimIndent()
        tvTax.text =("$" + cartViewModel.tax).trimIndent()

        progress_bar.visibility = View.GONE
        content.visibility = View.VISIBLE
    }

    override fun setError() {
        progress_bar.visibility = View.GONE

        val builder = AlertDialog.Builder(this)
        builder.setTitle("")
        builder.setMessage("System Unavailable")

        builder.setPositiveButton(resources.getString(R.string.txt_try_again_dialog)) { dialog, which ->
            progress_bar.visibility = View.VISIBLE
            interactor.getDataApi()
        }
        builder.show()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
