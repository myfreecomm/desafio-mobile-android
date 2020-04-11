package com.globo.raphaelbgr.desafio.brasileirao.base.util

import com.globo.raphaelbgr.desafio.data.network.response.MatchList
import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class TestUtilInstrumented {

    companion object {

        @Throws(IOException::class)
        fun loadBrasileiraoRawMockResponse(): String {
            val inputStream =
                this.javaClass.classLoader!!.getResourceAsStream("brasileirao_mock_response.json")
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val json = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                json.append(line)
            }
            inputStream?.close()
            return json.toString()
        }

        @Throws(IOException::class)
        fun loadBrasileiraoEmptyRawMockResponse(): String {
            val inputStream =
                this.javaClass.classLoader!!.getResourceAsStream("brasileirao_mock_response_empty.json")
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val json = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                json.append(line)
            }
            inputStream?.close()
            return json.toString()
        }

        @Throws(IOException::class)
        fun loadBrasileiraoMockSingleMatch(): Match {
            val inputStream =
                this.javaClass.classLoader!!.getResourceAsStream("brasileirao_mock_response.json")
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val json = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                json.append(line)
            }
            inputStream?.close()
            return Gson().fromJson(json.toString(), MatchList::class.java).matchList?.get(0)!!
        }
    }
}