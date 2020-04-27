package com.hotmail.fignunes.desafio_mobile.presentation.movie.actions

import android.content.Context
import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import com.hotmail.fignunes.desafio_mobile.repository.Repository
import com.hotmail.fignunes.desafio_mobile.repository.remote.TestApp
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(application = TestApp::class, sdk = [Build.VERSION_CODES.P])
class GetProductsTest {

    private lateinit var applicationContext: Context
    private lateinit var repository: Repository

    @Before
    fun before() {
        applicationContext = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        repository = Repository(applicationContext)
    }

    @Test
    fun `should return a list of movies`() {

        GetProducts(repository).execute()
            .test()
            .assertComplete()
    }
}