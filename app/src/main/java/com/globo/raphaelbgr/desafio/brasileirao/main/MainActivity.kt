package com.globo.raphaelbgr.desafio.brasileirao.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.globo.raphaelbgr.desafio.brasileirao.R
import com.globo.raphaelbgr.desafio.brasileirao.main.di.MainActivityModule
import com.globo.raphaelbgr.desafio.brasileirao.main.di.DaggerMainActivityComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependencies()
        title = getString(R.string.games_list)
        rv_games_list.adapter = MainActivityAdapter()
    }

    private fun injectDependencies() {
        DaggerMainActivityComponent.builder()
            .mainActivityModule(MainActivityModule())
            .build()
            .inject(this)
    }

    override fun showLoading(show: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onGamesListApiSuccess() {
        TODO("Not yet implemented")
    }

    override fun onGamesListApiFailure() {
        TODO("Not yet implemented")
    }

    override fun onGamesListCacheSuccess() {
        TODO("Not yet implemented")
    }

    override fun onGamesListCacheEmpty() {
        TODO("Not yet implemented")
    }
}
