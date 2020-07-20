package br.com.brunocardoso.studying.desafioandroidnexaasbruno.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    abstract val layout: Int

    abstract fun activityCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        activityCreated()
    }

    internal fun setupToolbar(
        toolbar: Toolbar,
        @StringRes titleIdRes: Int,
        showHomeAsUp: Boolean = false
    ) {
        toolbar.title = getString(titleIdRes)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(showHomeAsUp)
    }
}