package com.renanparis.desafioandroid.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.renanparis.desafioandroid.R
import com.renanparis.desafioandroid.ui.fragment.ProductsListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.topAppBar))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragment, ProductsListFragment())
        transaction.commit()
    }
}
