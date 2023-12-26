package com.dicoding.fishify.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoryResponseItem(
    val name: String,
    val photoUrl: String? = null,
    val id: String,
    val description: String,
    val lat: Double,
    val lon: Double,
    val ingredients: List<String>,
    val instructors: List<String>
) : Parcelable