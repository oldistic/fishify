package com.dicoding.fishify.model

import com.google.gson.annotations.SerializedName

data class RecipesResponse(

	@field:SerializedName("instructions")
	val instructions: List<String?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("ingredients")
	val ingredients: List<String?>? = null,

	@field:SerializedName("id")
	val id: Int? = null

)
