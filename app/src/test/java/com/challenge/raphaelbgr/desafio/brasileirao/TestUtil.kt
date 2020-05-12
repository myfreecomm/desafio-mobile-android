package com.challenge.raphaelbgr.desafio.brasileirao

import com.challenge.raphaelbgr.desafio.data.network.response.MatchList
import com.challenge.raphaelbgr.desafio.data.network.response.matchlist.Match
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class TestUtil {

    companion object {

        @Throws(IOException::class)
        fun loadBrasileiraoMockResponse(): List<Match> {
            val inputStream =
                this.javaClass.classLoader!!.getResourceAsStream("brasileirao_mock_response.json")
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val json = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                json.append(line)
            }
            inputStream?.close()
            return Gson().fromJson(json.toString(), MatchList::class.java).matchList!!
        }
    }
}