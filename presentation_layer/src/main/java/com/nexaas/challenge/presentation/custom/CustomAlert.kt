package com.nexaas.challenge.presentation.custom

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.nexaas.challenge.presentation.R

object CustomAlert {

    fun error(where: Context, description: String): AlertDialog {
        val layoutInflater = LayoutInflater.from(where)
        val confirmLayout = layoutInflater.inflate(R.layout.custom_alert_layout, null)
        val builder = AlertDialog.Builder(where, R.style.CustomAlert_Top).setView(confirmLayout)
        val dialog = builder.create()

        /* Layout Attrs */
        val closeImg = confirmLayout.findViewById<ImageView>(R.id.closeImg)
        val alertDescription = confirmLayout.findViewById<TextView>(R.id.alertDescription)
        closeImg.setOnClickListener { dialog.dismiss() }

        confirmLayout.setBackgroundColor(ContextCompat.getColor(where, R.color.colorAccent))
        alertDescription.text = description

        /* Force SBAlertBackground hack */
        val window = dialog.window!!
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        val params = window.attributes
        params.horizontalMargin = 0f
        params.gravity = Gravity.TOP
        params.dimAmount = 0f
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = params

        return dialog
    }

}