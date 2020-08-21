package com.example.challengeaccepted.platform.bindingadapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import com.example.challengeaccepted.platform.component.CustomLoadingDialog
import com.facebook.shimmer.ShimmerFrameLayout

@BindingAdapter("android:loadingShimmer")
fun setLoadingShimmer(view: ViewGroup, isLoading: Boolean) {
    val shimmer = view.findViewWithTag<ShimmerFrameLayout>("shimmer")
    val content = view.findViewWithTag<View>("not_shimmer")

    if(shimmer != null && content != null) {
        if(isLoading) {
            shimmer.visibility = View.VISIBLE
            content.visibility = View.INVISIBLE
            shimmer.startShimmer()
        }
        else {
            content.visibility = View.VISIBLE
            shimmer.visibility = View.INVISIBLE
            shimmer.stopShimmer()
        }
    }
    else {
        Log.e("SHIMMER LOADING", "Need to configure view tags 'shimmer' and 'not_shimmer'")
    }
}

@BindingAdapter("android:loadingDialog")
fun setLoadingDialog(view: ViewGroup, isLoading: Boolean) {
    if(isLoading) {
        view.tag = "loading"
        val dialog = CustomLoadingDialog(view.context)
        dialog.show()
        view.tag = dialog
    }
    else {
        if(view.tag != null) {
            val dialog = view.tag as CustomLoadingDialog
            dialog.dismiss()
            view.tag = null
        }
    }
}