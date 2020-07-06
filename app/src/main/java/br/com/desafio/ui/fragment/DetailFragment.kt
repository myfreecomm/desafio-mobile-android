package br.com.desafio.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import br.com.desafio.R
import br.com.desafio.databinding.DetailFragmentBinding
import br.com.desafio.ui.model.Item
import br.com.desafio.vm.DetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment(var item: Item?) : Fragment() {

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: DetailFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container,false)
        binding.root.transitionName = binding.root.transitionName
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewModel)

        viewModel.resultImageUrl.set(item?.image_url)
        viewModel.name.set(item?.name)
        viewModel.price.set("R$ " + item?.price)
        viewModel.description.set(item?.description)

        viewModel.voltar.observe(viewLifecycleOwner, Observer {
            if (it){
                fragmentManager?.popBackStack()
            }
        })
        return binding.root
    }
}