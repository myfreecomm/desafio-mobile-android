package com.challenge.raphaelbgr.desafio.brasileirao.base.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import com.challenge.raphaelbgr.desafio.data.network.response.MatchList
import com.challenge.raphaelbgr.desafio.data.network.response.matchlist.Match
import com.google.gson.Gson
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert
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

    class RecyclerViewItemCountAssertion(private val matcher: Matcher<Int>) :
        ViewAssertion {
        override fun check(
            view: View?,
            noViewFoundException: NoMatchingViewException?
        ) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            MatcherAssert.assertThat(adapter!!.itemCount, matcher)
        }
    }
}