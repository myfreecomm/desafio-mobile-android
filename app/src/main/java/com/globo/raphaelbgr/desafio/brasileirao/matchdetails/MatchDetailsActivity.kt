package com.globo.raphaelbgr.desafio.brasileirao.matchdetails

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.globo.raphaelbgr.desafio.brasileirao.R
import com.globo.raphaelbgr.desafio.brasileirao.util.BrasileiraoUtil
import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match
import kotlinx.android.synthetic.main.activity_match_details.*

class MatchDetailsActivity : AppCompatActivity() {

    private var match: Match? = null

    companion object {
        private const val MATCH_PARAM_STAVE_STATE = "MATCH_PARAM_STAVE_STATE"
        const val MATCH_PARAM = "MATCH_PARAM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        match = savedInstanceState?.getParcelable(MATCH_PARAM_STAVE_STATE)
        if (match == null) {
            match = intent?.getParcelableExtra(MATCH_PARAM)
        }
        if (match == null) finish()

        title = BrasileiraoUtil.getMatchName(match?.matchTeams)
        populateData()
    }

    private fun populateData() {
        tv_home_team_name.text = match?.matchTeams?.home?.teamName
        tv_away_team_name.text = match?.matchTeams?.away?.teamName
        tv_match_place.text = match?.matchPlace
        tv_home_team_score.text = match?.matchTeams?.home?.matchScore.toString()
        tv_away_team_score.text = match?.matchTeams?.away?.matchScore.toString()
        tv_match_date.text = BrasileiraoUtil.parseDate(match?.matchDate)

        BrasileiraoUtil.loadShieldImage(
            iv_home_team_shield,
            match?.matchTeams?.home?.teamShield
        )
        BrasileiraoUtil.loadShieldImage(
            iv_away_team_shield,
            match?.matchTeams?.away?.teamShield
        )
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
