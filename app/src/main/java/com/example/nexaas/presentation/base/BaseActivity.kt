package com.example.nexaas.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

    protected fun setupToolBar(toolbar: Toolbar, title: Int){
        toolbar.title = getString(title)
        setSupportActionBar(toolbar)
    }
}