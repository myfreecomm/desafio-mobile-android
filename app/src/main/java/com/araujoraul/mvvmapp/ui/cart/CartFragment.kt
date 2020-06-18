package com.araujoraul.mvvmapp.ui.cart

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.mvvmapp.R
import com.araujoraul.mvvmapp.db.ItemEntity

class CartFragment : Fragment(R.layout.fragment_cart), ItemCartClickListener {

    private val viewModel by lazy { ViewModelProvider(this).get(CartViewModel::class.java) }
    private val txtItemCount by lazy { view?.findViewById<TextView>(R.id.txtCountItems) }
    private val adapter by lazy { CartAdapter(emptyList(), this) }
    private val recyclerView by lazy { view?.findViewById<RecyclerView>(R.id.recyclerView_cart) }
    private val progressBar by lazy { view?.findViewById<ProgressBar>(R.id.progressBar_cart) }
    private val txtNoInternet by lazy { view?.findViewById<TextView>(R.id.noInternet) }
    private val fragManager by lazy { activity?.supportFragmentManager }
    private val fullDialog = DetailsDialogFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     viewModel.loadItems()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.text.observe(viewLifecycleOwner, Observer {
            txtItemCount?.text = it
        })

        viewModel.txtNoInternet.observe(viewLifecycleOwner, Observer {
            if(it) txtNoInternet?.visibility = View.VISIBLE
            else txtNoInternet?.visibility = View.GONE
        })

        viewModel.showProgress.observe(viewLifecycleOwner, Observer {
            if (it){
                progressBar?.visibility = View.VISIBLE
            }
            else {
                progressBar?.visibility = View.GONE
            }
        })

        viewModel.itemList.observe(viewLifecycleOwner, Observer {
                adapter.setItems(it)

        })

            setupRecyclerView()
    }

    fun setupRecyclerView(){
        recyclerView.let {
            it?.adapter = adapter
            it?.layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onItemClick(item: ItemEntity) {

        val args = Bundle()
        args.putString("title", item.name)
        args.putString("stock", item.stock.toString() )
        args.putString("price", item.price.toString() )
        args.putString("image", item.imageUrl)
        args.putString("description", item.description)

        fullDialog.arguments = args

        fullDialog.show(fragManager!!, "tag")

    }


}
