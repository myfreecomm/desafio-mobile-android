package br.com.mpc.android_challenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mpc.android_challenge.models.Item
import br.com.mpc.android_challenge.repository.ApplicationRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ApplicationRepository): ViewModel() {
    private val _listOfItems = MutableLiveData<ArrayList<Item>>()
    val listOfItems : LiveData<ArrayList<Item>> get() = _listOfItems

    fun getItems() {
        viewModelScope.launch {
            repository.getItems().map {
                _listOfItems.value = it
            }
        }
    }
}