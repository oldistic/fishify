package com.dicoding.fishify.ui.resep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.fishify.R
import com.dicoding.fishify.databinding.ActivityIngredientsBinding

class IngredientsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIngredientsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngredientsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.ingrediants)

        binding.ivtoner.setOnClickListener {
            val intent = Intent(this, MangutLeleActivity::class.java)
            startActivity(intent)
        }
    }
}