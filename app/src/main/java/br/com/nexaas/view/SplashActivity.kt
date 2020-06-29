package br.com.nexaas.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import br.com.nexaas.R
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val intent = Intent(this, MainActivity::class.java)
        Timer("SettingUp", false).schedule(500) {
            startActivity(intent)
            finish()
        }

    }
}