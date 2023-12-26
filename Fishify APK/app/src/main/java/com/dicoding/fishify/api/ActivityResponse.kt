package com.dicoding.fishify.api

import com.dicoding.fishify.model.StoryResponseItem
import com.google.gson.annotations.SerializedName

class ActivityResponse {
    data class FileUploadResponse(
        @field:SerializedName("error")
        val error: Boolean,

        @field:SerializedName("message")
        val message: String
    )

    data class SignUpResponse(
        @field:SerializedName("error")
        val error: Boolean,

        @field:SerializedName("message")
        val message: String
    )

    data class LoginResponse(
        @field:SerializedName("error")
        val error: Boolean,

        @field:SerializedName("message")
        val message: String,

        @field:SerializedName("loginResult")
        val loginResult: LoginResult? = null
    )

    data class LoginResult(
        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("token")
        val token: String
    )

    data class Recipe(
        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("ingredients")
        val ingredients: List<String>,

        @field:SerializedName("instructions")
        val instructions: List<String>

    )

    data class GetAllStoriesResponse(
        @field:SerializedName("error")
        val error: Boolean,

        @field:SerializedName("message")
        val message: String,

        @field:SerializedName("listStory")
        val listStory: List<StoryResponseItem>
    )

    data class UploadUpdateResponse(
        @field:SerializedName("error")
        val error: String? = null,

        @field:SerializedName("message")
        val message: String? = null,

        @field:SerializedName("statusCode")
        val statusCode: Int? = null
    )
}