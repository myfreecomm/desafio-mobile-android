package br.com.desafio.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.desafio.ui.model.LoginUser

class LoginViewModel : ViewModel() {

    var login = ObservableField<String>("")
    var userMutableLiveData: MutableLiveData<LoginUser?>? = null

    val user: MutableLiveData<LoginUser?>
        get() {
            if (userMutableLiveData == null) {
                userMutableLiveData = MutableLiveData()
            }
            return userMutableLiveData!!
        }

    fun onClick() {
        val loginUser = LoginUser(login.get())
        userMutableLiveData!!.value = loginUser
    }
}