package br.com.mpc.android_challenge.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout.VERTICAL
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mpc.android_challenge.R
import kotlinx.android.synthetic.main.fragment_first.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment : Fragment() {
    private val viewModel by viewModel<HomeViewModel>()
    private val mAdapter by lazy { ItemsAdapter() }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupButtons()
        subscribe()
    }

    private fun setupRecyclerView() {
        itemsRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    private fun setupButtons() {
        button_first.setOnClickListener {
            viewModel.getItems()
        }
    }

    private fun subscribe() {
        viewModel.listOfItems.observe(viewLifecycleOwner, Observer {
            mAdapter.updateItemsList(it)
        })
    }
}