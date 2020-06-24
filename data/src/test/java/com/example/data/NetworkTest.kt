package com.example.data

import com.example.data.repository.safeApiCall
import com.example.domain.repository.ResultWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

class NetworkTest {
    private val dispatcher = Dispatchers.IO

    @Test
    fun `Quando retornar do servidor sucesso, deve emitir o resultado como sucesso`() {
        CoroutineScope(Dispatchers.Main).launch {
            val lambdaResult = true
            val result = safeApiCall(dispatcher) { lambdaResult }
            assertEquals(ResultWrapper.Success(lambdaResult), result)
        }
    }

    @Test
    fun `Quanto o servidor retornar erro de conexão deve-se utilizar IOException`() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = safeApiCall(dispatcher) { throw IOException() }
            assertEquals(ResultWrapper.NetworkError, result)
        }
    }

    @Test
    fun `Quanto o servidor retornar uma exceção desconhecida, ele deve emitir GenericError`() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = safeApiCall(dispatcher) {
                throw IllegalStateException()
            }
            assertEquals(ResultWrapper.GenericError(), result)
        }
    }
}