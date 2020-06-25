package com.welbertsoft.androidteste.ui.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.welbertsoft.androidteste.R
import com.welbertsoft.androidteste.adapters.ItemAdapter
import com.welbertsoft.androidteste.repository.response.*
import java.text.DecimalFormat
import java.text.MessageFormat

class CartFragment : Fragment(),
    CartFragmentView {

    lateinit var txtTotalItens: TextView
    lateinit var rcItems: RecyclerView
    lateinit var cartFragmentPresenter: CartFragmentPresenter
    lateinit var txtPrice: TextView
    lateinit var txtSubTotal : TextView
    lateinit var txtShipping : TextView
    lateinit var txtTax : TextView


    fun initViews(view: View) {
        txtSubTotal = view.findViewById(R.id.txt_sub_total)
        txtShipping = view.findViewById(R.id.txt_shipping)
        txtTax = view.findViewById(R.id.txt_tax)
        txtPrice = view.findViewById(R.id.txt_price)
        txtTotalItens = view.findViewById(R.id.txt_total_items_label)
        rcItems = view.findViewById(R.id.recycle_items)
        rcItems.setHasFixedSize(true)
        rcItems.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_cart, container, false)
        initViews(root)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartFragmentPresenter = CartFragmentPresenter(this)
        cartFragmentPresenter.loadItens()

    }

    override fun onItensReceived(result: ArrayList<DataResponseElement>) {
        rcItems.adapter = ItemAdapter(requireContext(), result)
        txtTotalItens.setText(
            MessageFormat.format(
                getString(R.string.items_in_your_cart),
                result.size
            )
        )
        txtPrice.setText(DecimalFormat("##,##").format(result.getTotal()))
        txtTax.setText(DecimalFormat("##,##").format(result.getTax()))
        txtSubTotal.setText(DecimalFormat("##,##").format(result.getSubTotal()))
        txtShipping.setText(DecimalFormat("##,##").format(result.getShipping()))
    }

    override fun onConnectionFailed() {
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }


}
