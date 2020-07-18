package com.example.cartapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cartapp.R
import com.example.cartapp.databinding.FragmentItemBinding

class CartDetailsFragment  : Fragment(){
    protected lateinit var binding: FragmentItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentItemBinding>(inflater, R.layout.fragment_item, container, false)
        return binding.root
    }

}