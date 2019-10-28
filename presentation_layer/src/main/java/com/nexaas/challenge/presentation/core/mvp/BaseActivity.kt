package com.nexaas.challenge.presentation.core.mvp

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nexaas.challenge.presentation.R
import kotlinx.android.synthetic.main.toolbar.*

internal abstract class BaseActivity: AppCompatActivity(), BaseView {

    fun setupToolbar(title: String? = null, withReturnButton: Boolean = false) {
        toolbar?.let {
            it.bringToFront()
            it.title = title
            it.setNavigationIcon(R.drawable.ic_close_button)
            setSupportActionBar(it)
        }

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(withReturnButton)
            it.setDisplayShowTitleEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}