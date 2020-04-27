package com.hotmail.fignunes.desafio_mobile.common

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    protected fun backButton(drawable: Drawable? = null, color: Int? = null) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)


        drawable?.let {
            supportActionBar!!.setHomeAsUpIndicator(drawable)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        color?.let {
            supportActionBar!!.setBackgroundDrawable(ColorDrawable(color))
        }
    }
}