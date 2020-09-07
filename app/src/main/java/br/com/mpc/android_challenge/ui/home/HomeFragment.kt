package br.com.mpc.android_challenge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mpc.android_challenge.R
import br.com.mpc.android_challenge.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val viewModel by sharedViewModel<HomeViewModel>()
    private val sharedViewModel by sharedViewModel<MainViewModel>()
    private val navigator get() = findNavController()
    private val mAdapter by lazy {
        ItemsAdapter {
            sharedViewModel.item = it
            navigator.navigate(HomeFragmentDirections.actionHomeFragmentToItemDetailFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupButtons()
        subscribe()

        viewModel.getItems()
    }

    private fun setupRecyclerView() {
        itemsRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    private fun setupButtons() {
        refreshLayout.setOnRefreshListener {
            viewModel.getItems()
        }
    }

    private fun subscribe() {
        with(viewModel) {
            state.observe(viewLifecycleOwner, Observer {
                refreshLayout.isRefreshing = it.isLoading
                if (it.content != null) mAdapter.updateItemsList(it.content!!)
                if (it.error != null) Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            })
        }
    }
}