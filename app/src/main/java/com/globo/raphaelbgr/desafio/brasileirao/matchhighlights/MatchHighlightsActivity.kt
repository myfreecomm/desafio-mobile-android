package com.globo.raphaelbgr.desafio.brasileirao.matchhighlights

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.globo.raphaelbgr.desafio.brasileirao.R
import com.globo.raphaelbgr.desafio.brasileirao.util.BrasileiraoUtil
import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match
import kotlinx.android.synthetic.main.activity_main.*

class MatchHighlightsActivity : AppCompatActivity() {

    private var match: Match? = null
    private var adapter: HighlightsActivityAdapter? = null

    companion object {
        private const val MATCH_PARAM_STAVE_STATE = "MATCH_PARAM_STAVE_STATE"
        const val MATCH_PARAM = "MATCH_PARAM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highlights)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        match = savedInstanceState?.getParcelable(MATCH_PARAM_STAVE_STATE)
        if (match == null) {
            match = intent?.getParcelableExtra(MATCH_PARAM)
        }
        if (match == null && match?.matchHighlights.isNullOrEmpty()) finish()

        title = BrasileiraoUtil.getMatchNameHighlights(
            match?.matchTeams,
            getString(R.string.highlights)
        )
        setupRecyclerView()
        populateData()
    }

    private fun setupRecyclerView() {
        if (adapter == null)
            adapter = HighlightsActivityAdapter(match)
        rv_games_list.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            rv_games_list.context,
            (rv_games_list.layoutManager as LinearLayoutManager).orientation
        )
        rv_games_list.addItemDecoration(dividerItemDecoration)
    }

    private fun populateData() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        if (match == null) return
        outState.putParcelable(MATCH_PARAM_STAVE_STATE, match)
        super.onSaveInstanceState(outState, outPersistentState)
    }
}
