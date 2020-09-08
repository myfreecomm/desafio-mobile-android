package br.com.mpc.android_challenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mpc.android_challenge.models.Item
import br.com.mpc.android_challenge.repository.ApplicationRepository
import br.com.mpc.android_challenge.utils.Event
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(private val repository: ApplicationRepository) : ViewModel() {
    private val _state = MutableLiveData<Event<ArrayList<Item>>>()
    val state: LiveData<Event<ArrayList<Item>>> get() = _state

    fun getItems() {
        viewModelScope.launch {
            repository.getItems().map { _state.value = it }.collect()
        }
    }
}