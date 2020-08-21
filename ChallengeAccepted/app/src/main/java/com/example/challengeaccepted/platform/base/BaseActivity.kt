package com.example.challengeaccepted.platform.base

import android.app.ActivityOptions
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun revealBundle(v: View): Bundle? {
        var opts: ActivityOptions? = null
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                val left = 0
                val top = 0
                val width = 0 //v.measuredWidth
                val height = 0 //v.measuredHeight
                opts = ActivityOptions.makeClipRevealAnimation(v, left, top, width, height)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> opts = ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.measuredWidth, v.measuredHeight)
        }
        return opts?.toBundle()
    }
}
