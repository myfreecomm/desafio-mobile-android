package com.example.desafioandroid.ui.shared


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

abstract class BaseFragment : Fragment(){

    protected val navigatorController by lazy { NavHostFragment.findNavController(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListeners()
        addObservers()
    }

    abstract fun addListeners()

    abstract fun addObservers()
}