package br.com.nexaas.cart.detailsCart.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import br.com.nexaas.cart.R
import br.com.nexaas.cart.detailsCart.interactor.DetailsCartInteractor
import br.com.nexaas.cart.detailsCart.interactor.DetailsCartInteractorContract
import br.com.nexaas.cart.detailsCart.presenter.DetailsCartPresenter
import br.com.nexaas.cart.mainCart.model.CartViewModel
import br.com.nexaas.cart.model.CartItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details_item.*

class DetailsCartActivity : AppCompatActivity(), DetailsCartActivityContract {

    lateinit var toolbar: Toolbar
    private lateinit var cartItem: CartItem

    private lateinit var interactor: DetailsCartInteractorContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_item)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayShowCustomEnabled(true)

        cartItem = intent.getSerializableExtra("cartItem") as CartItem

        ViewCompat.setTransitionName(ivCartItemDetails, "ivCartItem")
        ViewCompat.setTransitionName(tvNameCartItemDetails, "nameCartItem")
        ViewCompat.setTransitionName(tvStockCartItemDetails, "stockCartItem")
        ViewCompat.setTransitionName(tvPriceCartItemDetails, "priceCartItem")

        initInteractor()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun initInteractor() {
        interactor = DetailsCartInteractor(DetailsCartPresenter(this))
        interactor.initComponents()
        interactor.getValuesCartItem(cartItem)
    }

    override fun initComponents() {
        btnClose.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    override fun setValuesCartItem(cartViewModel: CartViewModel) {
        Glide.with(this).load(cartViewModel.image).into(ivCartItemDetails);
        tvNameCartItemDetails.text = cartViewModel.name
        tvStockCartItemDetails.text = cartViewModel.stock
        tvPriceCartItemDetails.text = cartViewModel.price
        tvDescriptionCartItemDetails.text = cartViewModel.description
    }
}
