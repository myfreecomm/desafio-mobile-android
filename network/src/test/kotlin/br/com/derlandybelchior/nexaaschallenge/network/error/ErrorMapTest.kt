package br.com.derlandybelchior.nexaaschallenge.network.error

import br.com.derlandybelchior.nexaaschallenge.domain.error.NetworkError
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.net.SocketTimeoutException

class ErrorMapTest {
    private lateinit var errorMap: ErrorMap

    @Before fun setup() {
        errorMap = ErrorMap()
    }

    @Test fun `map should mapping to HostError`() {
        val error = errorMap.map(SocketTimeoutException())
        assertThat(error).isEqualTo(NetworkError.TimeoutError)
    }
}