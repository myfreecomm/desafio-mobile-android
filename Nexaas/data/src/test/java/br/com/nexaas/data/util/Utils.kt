package br.com.nexaas.data.util

import br.com.nexaas.network.util.fromJson

inline fun <reified T> getJson(path: String): T? {
    val string = ClassLoader.getSystemResource(path).readText()
    return fromJson(string)
}