package com.globo.raphaelbgr.desafio.brasileirao.main

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.globo.raphaelbgr.desafio.brasileirao.R
import com.globo.raphaelbgr.desafio.brasileirao.base.BaseActivity
import com.globo.raphaelbgr.desafio.brasileirao.main.di.DaggerMainActivityComponent
import com.globo.raphaelbgr.desafio.brasileirao.main.di.MainActivityModule
import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView, MatchListListener {

    private var adapter: MainActivityAdapter? = null

    @Inject
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependencies()
        title = getString(R.string.games_list)

        setupRecyclerView()
        presenter.setView(this)

        val savedList = savedInstanceState?.getParcelableArrayList<Match>(MATCH_PARAM_STAVE_STATE)
        if (savedList.isNullOrEmpty()) {
            presenter.getMatchList()
        } else {
            adapter?.setMatchList(savedList)
            adapter?.notifyDataSetChanged()
        }
    }

    private fun setupRecyclerView() {
        if (adapter == null)
            adapter = MainActivityAdapter(this)
        rv_games_list.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            rv_games_list.context,
            (rv_games_list.layoutManager as LinearLayoutManager).orientation
        )
        rv_games_list.addItemDecoration(dividerItemDecoration)
    }

    private fun injectDependencies() {
        DaggerMainActivityComponent.builder()
            .mainActivityModule(MainActivityModule(this))
            .build()
            .inject(this)
    }

    override fun showLoading(show: Boolean) {
        iv_camp_logo_loading.visibility = if (show) VISIBLE else GONE
        progress_bar_loading.visibility = if (show) VISIBLE else GONE
    }

    override fun onMatchListApiSuccess(list: List<Match>) {
        adapter?.setMatchList(list)
        adapter?.notifyDataSetChanged()
    }

    override fun onMatchListApiFailure() {
    }

    override fun onMatchListApiEmpty() {
    }

    override fun onMatchListCacheSuccess(localMatches: List<Match>) {
        adapter?.setMatchList(localMatches)
        adapter?.notifyDataSetChanged()
    }

    override fun onMatchListCacheEmpty() {
    }

    override fun onMatchClick(match: Match) {
        navigatorUtil.navigateToMatchDetails(match)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        val list = adapter?.getMatchList()
        if (list.isNullOrEmpty()) return
        outState.putParcelableArrayList(MATCH_PARAM_STAVE_STATE, adapter?.getMatchList())
        super.onSaveInstanceState(outState, outPersistentState)
    }

    companion object {
        private const val MATCH_PARAM_STAVE_STATE: String = "MATCH_PARAM_STAVE_STATE"
    }
}
