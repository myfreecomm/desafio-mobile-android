package com.renanparis.desafioandroid.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.renanparis.desafioandroid.R
import com.renanparis.desafioandroid.R.drawable.ic_close_black_24dp
import com.renanparis.desafioandroid.R.drawable.ic_reorder_white_24dp

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var navController: NavController
    private lateinit var listToolbar: Toolbar
    private lateinit var detailToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.activity_main_nav_host)
        listToolbar = findViewById(R.id.toolbar_list)
        detailToolbar = findViewById(R.id.toolbar_detail)
        setupActionBar()
        navController.addOnDestinationChangedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        val appBarConfig = AppBarConfiguration(navController.graph)
        return navController.navigateUp(appBarConfig)  || super.onSupportNavigateUp()
    }

    private fun setupActionBar() {
        val appBarConfig = AppBarConfiguration(setOf(R.id.productsListFragment, R.id.productDetailsFragment))

        if (listToolbar.visibility == View.VISIBLE) {
            setSupportActionBar(listToolbar)
            listToolbar.setupWithNavController(navController, appBarConfig)
            listToolbar.navigationIcon = getDrawable(ic_reorder_white_24dp)
        } else if (detailToolbar.visibility == View.VISIBLE) {
            setSupportActionBar(detailToolbar)
            detailToolbar.setupWithNavController(navController, appBarConfig)
            detailToolbar.navigationIcon = getDrawable(ic_close_black_24dp)
        }
    }
    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        if (destination.id == R.id.productDetailsFragment) {
            listToolbar.visibility = View.GONE
            detailToolbar.visibility = View.VISIBLE
            window.statusBarColor = ContextCompat.getColor(this,R.color.lightGray)

        } else {
            listToolbar.visibility = View.VISIBLE
            detailToolbar.visibility = View.GONE
            window.statusBarColor = ContextCompat.getColor(this,R.color.colorPrimaryDark)
        }
        setupActionBar()
    }
}
