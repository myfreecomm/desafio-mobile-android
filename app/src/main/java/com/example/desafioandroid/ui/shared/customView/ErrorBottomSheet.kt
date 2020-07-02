package com.example.desafioandroid.ui.shared.customView

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.desafioandroid.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ErrorBottomSheet : BottomSheetDialogFragment() {

    private lateinit var onDismissListener: () -> Unit

    fun newInstance(
         onDismissListener : () -> Unit
    ) : ErrorBottomSheet{
        return ErrorBottomSheet().apply {
            this.onDismissListener = onDismissListener
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.error_bottomsheet,container,false)
    }

    override fun onDismiss(dialog: DialogInterface) {
        if (::onDismissListener.isInitialized){
            onDismissListener()
        }
        super.onDismiss(dialog)
    }
}