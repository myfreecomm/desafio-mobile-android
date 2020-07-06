package br.com.desafio.mock

import br.com.desafio.response.LoginResponse.loginResnponseSucess
import java.util.*

class DefaultConfigurationMockResponse {

    private val mapResponse: HashMap<String, Any> = HashMap()

    fun response(URI: String?): Any? {
        return mapResponse[URI]
    }

    private fun createResponse() {
        mapResponse["https://raw.githubusercontent.com/"] = loginResnponseSucess
    }

    init {
        createResponse()
    }
}