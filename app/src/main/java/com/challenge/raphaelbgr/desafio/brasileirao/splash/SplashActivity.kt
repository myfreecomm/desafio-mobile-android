package com.challenge.raphaelbgr.desafio.brasileirao.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.challenge.raphaelbgr.desafio.brasileirao.R
import com.challenge.raphaelbgr.desafio.brasileirao.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
