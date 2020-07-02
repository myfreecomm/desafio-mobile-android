package br.com.derlandybelchior.nexaaschallenge.network.error

import br.com.derlandybelchior.nexaaschallenge.domain.error.NetworkError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@ExperimentalCoroutinesApi
class ErrorMapTest {
    private lateinit var errorMap: ErrorMap

    @Before fun setup() {
        errorMap = ErrorMap()
    }

    @Test fun `map should mapping to TimeoutError`() {
        val error = errorMap.map(SocketTimeoutException())
        assertThat(error).isEqualTo(NetworkError.TimeoutError)
    }

    @Test fun `map should map httpException` () {

        runBlockingTest {
            val result = runCatching {
                simulateError(404, "Resource not found")
            }
            val error = errorMap.map(result.exceptionOrNull()!!)
            assertThat(error).isEqualTo(NetworkError.HostError)
        }

    }

    private suspend fun simulateError(status: Int, errorMessage: String) {
        suspendCoroutine<Unit> { continuation ->
            val exception = when(status) {
                in 300..599 -> HttpException(Response.error<Any>(status, errorMessage.toResponseBody()))
                else -> Exception(errorMessage)
            }
            continuation.resumeWithException(exception)
        }
    }
}