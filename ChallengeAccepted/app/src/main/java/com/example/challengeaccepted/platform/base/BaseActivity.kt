package com.example.challengeaccepted.platform.base

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.challengeaccepted.R
import com.example.challengeaccepted.platform.component.CustomLoadingDialog
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {
    private var vShimmer: ShimmerFrameLayout? = null
    private var vContent: View? = null
    private var loadingDialog: CustomLoadingDialog? = null

    @IdRes
    protected open fun setShimmerLayoutId(): Int? {
        return null
    }

    @IdRes
    protected open fun setContentShimmerLayoutId(): Int? {
        return null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("ResourceType")
    private fun startShimmer() {
        if (setShimmerLayoutId() != null && setContentShimmerLayoutId() != null) {
            vShimmer = findViewById<ShimmerFrameLayout>(setShimmerLayoutId()!!)
            vContent = findViewById<View>(setContentShimmerLayoutId()!!)
        }


        if (vShimmer != null && vContent != null) {
            vShimmer?.visibility = View.VISIBLE
            vContent?.visibility = View.GONE
            vShimmer?.startShimmer()
        } else {
            showLoading()
        }
    }

    private fun stopShimmer() {
        if (vShimmer != null && vContent != null) {
            vShimmer?.stopShimmer()
            vShimmer?.visibility = View.GONE
            vContent?.visibility = View.VISIBLE
        } else {
            hideLoading()
        }
    }

    private fun showLoading() {
        runOnUiThread {
            if (this.loadingDialog == null) {
                this.loadingDialog = CustomLoadingDialog(this)
            }
            if (!this.loadingDialog!!.isShowing)
                this.loadingDialog!!.show()
        }
    }

    open fun showLoading(isShimmer: Boolean = false) {
        if (isShimmer) {
            startShimmer()
        } else {
            showLoading()
        }
    }

    private fun hideLoading() {
        runOnUiThread {
            if (this.loadingDialog != null && loadingDialog!!.isShowing)
                this.loadingDialog!!.dismiss()
        }
    }

    fun hideLoading(isShimmer: Boolean = false) {
        if (isShimmer) {
            stopShimmer()
        } else {
            hideLoading()
        }
    }

    open fun showSuccessMessage(view: View?, @StringRes resourceId: Int) {
        showSuccessMessage(view, getText(resourceId))
    }

    open fun showSuccessMessage(view: View?, message: CharSequence) {
//        showSuccessMessage(view, message.toString())
    }

    open fun showSuccessMessage(@StringRes resourceId: Int) {
        showSuccessMessage(window.decorView, resourceId)
    }

    open fun showSuccessMessage(view: View?, message: String?) {
        showMessage(view, message, true)
    }

    open fun showSuccessMessage(message: String?) {
        showMessage(window.decorView, message, true)
    }

    open fun showError(message: String?, redirect: Boolean) {
        showErrorMessage(message)
    }

    open fun showErrorMessage(message: String?) {
        showErrorMessage(window.decorView, message)
    }

    open fun showErrorMessage(@StringRes resourceId: Int) {
        showErrorMessage(window.decorView, resourceId)
    }

    open fun showErrorMessage(view: View?, @StringRes resourceId: Int) {
        showErrorMessage(view, getString(resourceId))
    }

    open fun showErrorMessage(view: View?, message: String?) {
        showMessage(view, message, false)
    }

    open fun showErrorMessage(view: View?, message: String?, callback: BaseTransientBottomBar.BaseCallback<Snackbar?>?) {
        showMessage(view, message, false, callback)
    }

    open fun showMessageError(message: String?) {
        showErrorMessage(message)
    }

    open fun showMessage(view: View?, @StringRes message: Int, success: Boolean) {
        showMessage(view, getString(message), success)
    }

    open fun showMessage(view: View?, message: String?, success: Boolean) {
        showMessage(view, message, success, null)
    }

    open fun showMessage(view: View?, @StringRes message: Int, success: Boolean,
                         callback: BaseTransientBottomBar.BaseCallback<Snackbar?>?) {
        showMessage(view, getString(message), success, callback)
    }

    @SuppressLint("WrongConstant")
    open fun showMessage(view: View?, message: String?, success: Boolean,
                         callback: BaseTransientBottomBar.BaseCallback<Snackbar?>?) {
        try {
            val snackbar = Snackbar.make(view!!, message!!, Snackbar.LENGTH_LONG)
            val snackbarView = snackbar.view
            snackbarView
                .setBackgroundColor(ContextCompat.getColor(this, if (success) R.color.snackbar_success else R.color.snackbar_error))
            val textView = snackbarView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
            textView.setTextColor(Color.WHITE)
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            textView.gravity = Gravity.CENTER_HORIZONTAL
            textView.isSingleLine = false
            if (callback != null) snackbar.addCallback(callback)
            snackbar.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("WrongConstant")
    open fun showWarningMessage(view: View?, message: String?) {
        var view = view
        if (view == null) view = window.decorView
        val snackbar = Snackbar.make(view!!, message!!, Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.black))
        val textView = snackbarView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textView.gravity = Gravity.CENTER_HORIZONTAL
        textView.isSingleLine = false
        snackbar.show()
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
