package com.dicoding.fishify.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("/register")
    fun registerAccount(
        @Field("username") username :String,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ActivityResponse.SignUpResponse>

    @FormUrlEncoded
    @POST("/login")
    fun loginAccount(
        @Field("email") name: String,
        @Field("password") email: String
    ): Call<ActivityResponse.LoginResponse>

    @Multipart
    @POST("/upload-image")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
        @Part("error") error: RequestBody,
        @Part("massage") massage: RequestBody,
        @Part("statusCode") statusCode: RequestBody
    ): ActivityResponse.UploadUpdateResponse

    @Multipart
    @POST("upload-and-detect-fish")
    suspend fun uploadDetect(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
        @Part("error") error: String,
        @Part("massage") massage: String,
        @Part("statusCode") statusCode: Int,
    )

    @GET("/upload-and-detect-fish")
    suspend fun getStoriesList(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("location") location: Int = 0
    ): ActivityResponse.GetAllStoriesResponse

    @GET("v1/stories")
    fun getStoriesWithLocation(
        @Header("Authorization") token: String,
        @Query("location") includeLocation: Int = 1
    ): Call<ActivityResponse.GetAllStoriesResponse>

    @GET("/recipes/1")
    suspend fun getRecipe(
        @Header("Authorization") token: String,
        @Query("id") id: Int,
        @Query("name") name: String,
        @Query("ingredients")  ingredients: List<String>,
        @Query("instructions") instructions:  List<String>
    ): ActivityResponse.Recipe
}