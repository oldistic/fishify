package com.dicoding.fishify.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Url

@Parcelize
data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val photoUrl: String
): Parcelable