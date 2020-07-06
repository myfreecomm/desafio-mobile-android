package br.com.desafio.ui.model

class LoginUser(val strLogin: String?) {

    val isLoginLengthGreaterThan5: Boolean
        get() = strLogin!!.length > 5
}