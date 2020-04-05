package com.globo.raphaelbgr.desafio.brasileirao.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.globo.raphaelbgr.desafio.brasileirao.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.games_list)
        rv_games_list.adapter = MainActivityAdapter()
    }
}
