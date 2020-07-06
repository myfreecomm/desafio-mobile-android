package br.com.desafio

import android.util.Log
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import br.com.desafio.mock.MockServer
import br.com.desafio.response.LoginResponse.loginResnponseSucess
import br.com.desafio.ui.activity.LoginActivity
import br.com.desafio.ui.activity.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginTest { // Exemplo de teste integrado com Espresso

    @get:Rule
    val activityRule = ActivityTestRule(LoginActivity::class.java)

    var mockServer: MockServer? = null

    @Before
    fun setUp() {
        if (mockServer == null) {
            mockServer = MockServer()
        }
    }

    @After
    fun cleanUp() {
        mockServer?.stop()
    }

    @Test
    fun logar() {
        Log.i("*** ", "Inicio teste de login")
        Intents.init()
        mockServer!!.changeResponse(loginResnponseSucess)
        Espresso.onView(ViewMatchers.withId(R.id.edtLogin)).perform(ViewActions.typeText("123456"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withText("Login")).perform(ViewActions.click())
        Intents.intended(hasComponent(MainActivity::class.java.name))
        Log.i("*** ", "Fim teste de login")
        Intents.release()
    }
}