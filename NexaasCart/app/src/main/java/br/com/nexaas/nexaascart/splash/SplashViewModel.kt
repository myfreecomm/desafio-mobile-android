package br.com.nexaas.nexaascart.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nexaas.nexaascart.livedata.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SplashViewModel(
    private val dispatcher: CoroutineContext = Dispatchers.Main + SupervisorJob()
) : ViewModel() {

    private val mutableCommand = SingleLiveEvent<SplashCommand>()
    val command: LiveData<SplashCommand> = mutableCommand

    fun init() {
        viewModelScope.launch(dispatcher) {
            SplashCommand.OpenHome.run()

        }
    }

    fun SplashCommand.run() {
        mutableCommand.postValue(this)
    }

}
