package br.com.desafio.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.desafio.R
import br.com.desafio.databinding.ActivityLoginBinding
import br.com.desafio.vm.LoginViewModel
import java.util.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        var loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel

        loginViewModel.user.observe(this, Observer { it ->
            it?.let {
                if (TextUtils.isEmpty(Objects.requireNonNull(it).strLogin)) {
                    binding.edtLogin.error = "Inserir login"
                    binding.edtLogin.requestFocus()
                } else if (!it!!.isLoginLengthGreaterThan5) {
                    binding.edtLogin.error = "6 dígitos"
                    binding.edtLogin.requestFocus()
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        })
    }
}