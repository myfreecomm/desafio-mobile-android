package com.appdesafio.cart
import org.junit.Assert.*
import org.junit.Test
import com.appdesafio.cart.services.ApiConfig.Companion.url
import java.io.IOException
import java.net.URL
import java.net.HttpURLConnection

class VerificaPrincipais {
    @Test
    fun getStatusApi(urlPath:String) {
        try
        { val urlCheck = URL(url)
            val conn = urlCheck.openConnection() as HttpURLConnection
            conn.setRequestMethod("GET")
            conn.connect()
            var result:Boolean
            if (conn.getResponseCode() === 200) {
                result = true
                assertTrue(result)
            }else{
                result = false
                assertFalse(result)
            }
        }
        catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}
