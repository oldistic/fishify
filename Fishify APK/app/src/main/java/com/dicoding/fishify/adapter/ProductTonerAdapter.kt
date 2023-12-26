package com.dicoding.fishify.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.fishify.R
import com.dicoding.fishify.model.ProductImage

class ProductTonerAdapter(
    private val context: Context,
    private val images: List<ProductImage>
) : RecyclerView.Adapter<ProductTonerAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = itemView.findViewById<ImageView>(R.id.producttoner)
        fun bindView(image: ProductImage) {
            img.setImageResource(image.imageSrc)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(
            LayoutInflater.from(context).inflate(R.layout.toner_item, parent, false)
        )


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(images[position])
    }


    override fun getItemCount(): Int = images.size

}