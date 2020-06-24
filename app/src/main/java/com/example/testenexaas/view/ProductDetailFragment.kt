package com.example.testenexaas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.testenexaas.R
import com.example.testenexaas.extensions.stockToString
import com.example.testenexaas.extensions.toCurrency
import com.example.testenexaas.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductDetailFragment : DialogFragment() {

    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.dialog_theme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.charactersDetail.observe(viewLifecycleOwner, Observer {
            view?.apply {
                context?.also {context->
                    Glide.with(context)
                        .load(it.image_url)
                        .into(findViewById(R.id.iv_product_image_detail))
                }

                findViewById<AppCompatTextView>(R.id.tv_product_title_detail).text = it.name
                findViewById<AppCompatTextView>(R.id.tv_product_price_detail).text = it.price.toCurrency()
                findViewById<AppCompatTextView>(R.id.tv_product_subtitle_detail).text = it.stock.stockToString()
                findViewById<AppCompatTextView>(R.id.tv_product_description_detail).text = it.description
                findViewById<AppCompatButton>(R.id.btn_close).setOnClickListener {
                    dismiss()
                }
            }
        })
    }

    companion object {
        fun newInstance() = ProductDetailFragment()
    }

}