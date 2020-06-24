package br.com.mob9.cart.application.ui.cart

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.mob9.cart.R
import br.com.mob9.cart.core.base.BaseActivity
import br.com.mob9.cart.core.di.ViewModelFactory
import br.com.mob9.cart.databinding.ActivityCartBinding
import br.com.mob9.cart.device.ConnectionHelper
import javax.inject.Inject

class CartActivity : BaseActivity() {
    @Inject
    lateinit var connection: ConnectionHelper

    lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart)

        connection.observe(this, Observer {
            binding.tvStatus.text = if (it) "Conectado" else "Sem conexão"
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> {
                Toast.makeText(this, "Buscar", Toast.LENGTH_LONG)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}