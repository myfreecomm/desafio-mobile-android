package com.nexaas.challenge.data.remote

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import java.net.HttpURLConnection

class ApiRepositoryImplTest {

    private lateinit var mockWebServer: MockWebServer
    private val serverPort = 9999
    private val serverPath = "mockserver/"

    private lateinit var repositoryImpl: ApiRepositoryImpl

    @Before
    fun init() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        mockWebServer = MockWebServer()
        mockWebServer.start(serverPort)

        repositoryImpl = ApiRepositoryImpl(mockWebServer.url(serverPath).toString())
    }

    @After
    fun clean() {
        mockWebServer.shutdown()
    }

    @Test
    fun testRequestSuccess() {
        val body = this.javaClass.classLoader?.getResourceAsStream("api-returns/api_return_data_example.json")?.bufferedReader().use { it?.readText() }
        mockWebServer.enqueue(MockResponse().setBody(body).setResponseCode(HttpURLConnection.HTTP_OK))

        val testObservable = repositoryImpl.getProductsList().test()
        val result = testObservable.values().first()
        testObservable.assertComplete()
        testObservable.assertNoErrors()
        testObservable.dispose()

        assert(!result.isNullOrEmpty())
    }

    @Test
    fun testRequestEmptyBody() {
        mockWebServer.enqueue(MockResponse().setBody("").setResponseCode(HttpURLConnection.HTTP_OK))

        val testObservable = repositoryImpl.getProductsList().test()
        val productsList = testObservable.values()
        assert(productsList.isNullOrEmpty())
    }

    @Test
    fun testRequestUnauthorized() {
        mockWebServer.enqueue(MockResponse().setBody("").setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED))

        val testObservable = repositoryImpl.getProductsList().test()
        testObservable.assertError(HttpException::class.java)
    }

    @Test
    fun testRequestNotFound() {
        mockWebServer.enqueue(MockResponse().setBody("").setResponseCode(HttpURLConnection.HTTP_NOT_FOUND))

        val testObservable = repositoryImpl.getProductsList().test()
        testObservable.assertError(HttpException::class.java)
    }

    @Test
    fun testRequestInternalServerError() {
        mockWebServer.enqueue(MockResponse().setBody("").setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR))

        val testObservable = repositoryImpl.getProductsList().test()
        testObservable.assertError(HttpException::class.java)
    }

}