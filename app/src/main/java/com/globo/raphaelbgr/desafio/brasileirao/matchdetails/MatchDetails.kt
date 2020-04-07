package com.globo.raphaelbgr.desafio.brasileirao.matchdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.globo.raphaelbgr.desafio.brasileirao.R

class MatchDetails : AppCompatActivity() {

    companion object {
        const val MATCH_PARAM = "MATCH_PARAM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_details)
    }
}
