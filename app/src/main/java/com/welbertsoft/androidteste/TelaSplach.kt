package com.welbertsoft.androidteste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TelaSplach : AppCompatActivity() {


    //Activity to inicialize things

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach)
        start()
    }

    fun start(){
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}
