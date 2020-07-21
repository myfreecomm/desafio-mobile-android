package br.com.brunocardoso.studying.desafioandroidnexaasbruno.base

import android.os.Bundle
import android.transition.Fade
import android.view.View
import android.widget.TextView
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.details_toolbar.*

abstract class BaseActivity : AppCompatActivity() {

    abstract val layout: Int

    abstract fun activityCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fade().apply {
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }.run {
            window.enterTransition = this
            window.exitTransition = this
        }
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


    internal fun setupToolbarCustom(
        toolbar: Toolbar,
        titleViewRes: TextView,
        @StringRes titleIdRes: Int,
        backButtonViewRes: View
    ) {
        toolbar.title = ""
        titleViewRes.text = getString(titleIdRes)
        backButtonViewRes.setOnClickListener { onBackPressed() }
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}