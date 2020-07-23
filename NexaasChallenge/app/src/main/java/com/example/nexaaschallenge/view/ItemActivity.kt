package com.example.nexaaschallenge.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.navArgs
import com.example.nexaaschallenge.R.layout.activity_item
import com.example.nexaaschallenge.databinding.ActivityItemBinding

class ItemActivity : AppCompatActivity(){
    protected lateinit var binding: ActivityItemBinding
    private val args by navArgs<ItemActivityArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityItemBinding>(this, activity_item).apply { lifecycleOwner = this@ItemActivity }
        binding.itemCart = args.item
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}