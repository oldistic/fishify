package com.dicoding.fishify.model

import com.google.gson.annotations.SerializedName

data class UploadDetectResponse(

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null

)
