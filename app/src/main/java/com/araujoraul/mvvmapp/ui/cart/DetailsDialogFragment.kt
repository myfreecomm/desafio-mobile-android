package com.araujoraul.mvvmapp.ui.cart

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.araujoraul.mvvmapp.R
import com.araujoraul.mvvmapp.extension.loadImage

class DetailsDialogFragment : DialogFragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DetailsDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_dialog_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnImageClose = view.findViewById<ImageButton>(R.id.btn_image_close)
        val btnRemoveFromCart = view.findViewById<Button>(R.id.btnRemoveFromCart)

        val args: Bundle? = arguments

        val title: String? = args?.getString("title")
        val stock: String? = args?.getString("stock")
        val price: String? = args?.getString("price")
        val description: String? = args?.getString("description")
        val image: String? = args?.getString("image")

        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtStock = view.findViewById<TextView>(R.id.txtStock)
        val txtPrice = view.findViewById<TextView>(R.id.txtPrice)
        val txtDescription = view.findViewById<TextView>(R.id.txtDescription)
        val mImage = view.findViewById<ImageView>(R.id.image)

        txtTitle?.text = title
        txtStock?.text = stock+" in stock"
        txtPrice?.text = price
        txtDescription?.text = description
        mImage?.loadImage(image)

        btnImageClose?.setOnClickListener{ dismiss() }
        btnRemoveFromCart?.setOnClickListener { Toast.makeText(activity?.applicationContext, "Not Implemented", Toast.LENGTH_SHORT).show() }

    }


}
