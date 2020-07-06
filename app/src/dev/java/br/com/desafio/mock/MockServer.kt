package br.com.desafio.mock

import com.google.gson.Gson
import com.squareup.okhttp.mockwebserver.Dispatcher
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import com.squareup.okhttp.mockwebserver.RecordedRequest
import java.io.IOException
import java.net.InetAddress
import java.util.logging.Logger

class MockServer {

    private var mockWebServer: MockWebServer? = null

    fun changeResponse(responseObject: Any?) {
        mockWebServer!!.setDispatcher(AlterDispatcher(responseObject))
    }

    private inner class DefaultConfigurationDispatcher : Dispatcher() {
        @Throws(InterruptedException::class)
        override fun dispatch(request: RecordedRequest): MockResponse {
            val mockResponse = MockResponse()
            val defaultConfigurationMockResponse =
                DefaultConfigurationMockResponse()
            val response = defaultConfigurationMockResponse.response(request.path)
            val responseJson = Gson().toJson(response)
            logger.info("------------------------------ --------------- --------------- --------------- --------------- --------------- --------------- ---------------  ")
            logger.info("REQUEST PATH: " + request.path)
            logger.info("RESPONSE JSON: $responseJson")
            mockResponse
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .setBody(responseJson)
            return mockResponse
        }
    }

    private inner class AlterDispatcher(private val responseObject: Any?) :
        Dispatcher() {
        @Throws(InterruptedException::class)
        override fun dispatch(request: RecordedRequest): MockResponse {
            val gson = Gson()
            val mockResponse = MockResponse()
            mockResponse.setResponseCode(200)
            val responseJson = gson.toJson(responseObject)
            logger.info("RESPONSE ON DISPATCHER: $responseJson")
            mockResponse.setBody(gson.toJson(responseObject))
            return mockResponse
        }

    }

    fun stop() {
        try {
            mockWebServer!!.shutdown()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        private val logger =
            Logger.getLogger(MockServer::class.java.name)
    }

    init {
        try {
            mockWebServer = MockWebServer()
            mockWebServer!!.setDispatcher(DefaultConfigurationDispatcher())
            logger.info(InetAddress.getLocalHost().hostAddress)
            val address = InetAddress.getLocalHost().hostAddress
            val inetAddress = InetAddress.getByName(address)
            mockWebServer!!.start(inetAddress, 8080)
        } catch (e: IOException) {
            logger.info("Error when try start mock server ..." + e.message + e.cause.toString())
        }
    }
}