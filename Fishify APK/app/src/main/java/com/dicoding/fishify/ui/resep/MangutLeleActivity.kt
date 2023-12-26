package com.dicoding.fishify.ui.resep

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dicoding.fishify.R

class MangutLeleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mangul_resep)

        val imageView: ImageView = findViewById(R.id.youtubeThumbnail)
        val videoId = "9eawRQbFVCM" // Ganti dengan ID video YouTube yang ingin Anda buka

        imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$videoId"))
            startActivity(intent)
        }
    }
}