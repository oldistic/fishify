package com.dicoding.fishify.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.paging.*
import com.dicoding.fishify.api.ActivityResponse
import com.dicoding.fishify.api.ApiService
import com.dicoding.fishify.data.StoryDataSource
import com.dicoding.fishify.model.StoryResponseItem
import com.dicoding.fishify.model.TokenModel
import com.dicoding.fishify.model.UserPreference
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository private constructor(
    private val pref: UserPreference,
    private val apiService: ApiService
) {
    private val _signUpResponse = MutableLiveData<ActivityResponse.SignUpResponse>()
    val signUpResponse: LiveData<ActivityResponse.SignUpResponse> = _signUpResponse

    private val _loginResponse = MutableLiveData<ActivityResponse.LoginResponse>()
    val loginResponse: LiveData<ActivityResponse.LoginResponse> = _loginResponse

    private val _loginResult = MutableLiveData<ActivityResponse.LoginResult?>()
    val loginResult: MutableLiveData<ActivityResponse.LoginResult?> = _loginResult

    private val _fileUploadResponse = MutableLiveData<ActivityResponse.UploadUpdateResponse>()
    val fileUploadResponse: LiveData<ActivityResponse.UploadUpdateResponse> = _fileUploadResponse

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _showLoading

    private val _getAllStoriesResponse = MutableLiveData<ActivityResponse.GetAllStoriesResponse>()
    val getAllStoriesResponse: LiveData<ActivityResponse.GetAllStoriesResponse> = _getAllStoriesResponse

    private val _toastText = MutableLiveData<String?>()
    val toastText: MutableLiveData<String?> = _toastText

    fun registerAccount(username: String, name: String, email: String, password: String) {
        _showLoading.value = true
        val client = apiService.registerAccount(username, name, email, password)

        client.enqueue(object : Callback<ActivityResponse.SignUpResponse> {
            override fun onResponse(
                call: Call<ActivityResponse.SignUpResponse>,
                response: Response<ActivityResponse.SignUpResponse>
            ) {
                _showLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    _signUpResponse.value = response.body()
                    _toastText.value = response.body()?.message
                } else {
                    _toastText.value = response.message().toString()
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}, ${response.body()?.message.toString()}"
                    )
                }
            }

            override fun onFailure(call: Call<ActivityResponse.SignUpResponse>, t: Throwable) {
                _toastText.value = t.message.toString()
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun loginAccount(email: String, password: String) {
        _showLoading.value = true
        val client = apiService.loginAccount(email, password)

        client.enqueue(object : Callback<ActivityResponse.LoginResponse> {
            override fun onResponse(
                call: Call<ActivityResponse.LoginResponse>,
                response: Response<ActivityResponse.LoginResponse>
            ) {
                _showLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    _loginResponse.value = response.body()
                    _toastText.value = response.body()?.message
                    _loginResult.value = response.body()?.loginResult
                } else {
                    _toastText.value = response.message().toString()
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}, ${response.body()?.message.toString()}"
                    )
                }
            }

            override fun onFailure(call: Call<ActivityResponse.LoginResponse>, t: Throwable) {
                _toastText.value = t.message.toString()
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    // Fungsi untuk mengunggah cerita
    suspend fun uploadStory(token: String, file: MultipartBody.Part, error: RequestBody, massage: RequestBody, statusCode: RequestBody) {
        _showLoading.value = true
        try {
            val response = apiService.uploadImage(token, file, error, massage, statusCode)
            _showLoading.value = false
            if (response != null && response.error.isNullOrEmpty()) {
                _fileUploadResponse.value = response
                _toastText.value = response.message
            } else {
                _toastText.value = response?.message
            }
        } catch (t: Throwable) {
            _toastText.value = t.message.toString()
            Log.e(TAG, "onFailure: ${t.message.toString()}")
        } finally {
            _showLoading.value = false
        }
    }

    fun getStoriesList(token : String): LiveData<PagingData<StoryResponseItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                StoryDataSource(apiService, token)
            }
        ).liveData
    }

    fun getStoriesWithLocation(token: String) {
        _showLoading.value = true
        val client = apiService.getStoriesWithLocation(token)
        Log.d("TOKEN", token)

        client.enqueue(object : Callback<ActivityResponse.GetAllStoriesResponse> {
            override fun onResponse(
                call: Call<ActivityResponse.GetAllStoriesResponse>,
                response: Response<ActivityResponse.GetAllStoriesResponse>
            ) {
                _showLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    _getAllStoriesResponse.value = response.body()
                } else {
                    _toastText.value = response.message().toString()
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}, ${response.body()?.message.toString()}"
                    )
                }
            }

            override fun onFailure(call: Call<ActivityResponse.GetAllStoriesResponse>, t: Throwable) {
                _toastText.value = t.message.toString()
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun loadState(): LiveData<TokenModel> {
        return pref.loadState().asLiveData()
    }

    suspend fun saveState(session: TokenModel) {
        pref.saveState(session)
    }

    suspend fun login() {
        pref.login()
    }

    suspend fun logout() {
        pref.logout()
    }

    companion object {
        private const val TAG = "Repository"

        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            preferences: UserPreference,
            apiService: ApiService
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(preferences, apiService)
            }.also { instance = it }
    }
}