package com.globo.raphaelbgr.desafio.brasileirao.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.globo.raphaelbgr.desafio.brasileirao.R
import com.globo.raphaelbgr.desafio.brasileirao.main.di.DaggerMainActivityComponent
import com.globo.raphaelbgr.desafio.brasileirao.main.di.MainActivityModule
import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainView {

    private lateinit var adapter: MainActivityAdapter

    @Inject
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependencies()
        title = getString(R.string.games_list)

        setupRecyclerView()
        presenter.setView(this)
        presenter.getMatchList()
    }

    private fun setupRecyclerView() {
        adapter = MainActivityAdapter()
        rv_games_list.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            rv_games_list.context,
            (rv_games_list.layoutManager as LinearLayoutManager).orientation
        )
        rv_games_list.addItemDecoration(dividerItemDecoration)
    }

    private fun injectDependencies() {
        DaggerMainActivityComponent.builder()
            .mainActivityModule(MainActivityModule())
            .build()
            .inject(this)
    }

    override fun showLoading(show: Boolean) {
    }

    override fun onMatchListApiSuccess(list: List<Match>) {
        adapter.setMatchList(list)
        adapter.notifyDataSetChanged()
    }

    override fun onMatchListApiFailure() {
    }

    override fun onMatchListApiEmpty() {
    }

    override fun onMatchListCacheSuccess() {
    }

    override fun onMatchListCacheEmpty() {
    }
}
