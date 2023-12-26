package com.dicoding.fishify.ui.main.Recomendation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.fishify.R
import com.dicoding.fishify.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.EComerce)
    }
}
