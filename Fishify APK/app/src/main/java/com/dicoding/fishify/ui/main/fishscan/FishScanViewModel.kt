package com.dicoding.fishify.ui.main.fishscan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.fishify.api.ActivityResponse
import com.dicoding.fishify.model.TokenModel
import com.dicoding.fishify.repository.Repository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class FishScanViewModel(private val pref: Repository) : ViewModel() {
    val fileUploadResponse: LiveData<ActivityResponse.UploadUpdateResponse> = pref.fileUploadResponse
    val showLoading: LiveData<Boolean> = pref.showLoading
    val toastText: MutableLiveData<String?> = pref.toastText

    fun uploadStory(token: String, file: MultipartBody.Part, error: RequestBody, massage: RequestBody, statusCode: RequestBody) {
        viewModelScope.launch {
            pref.uploadStory(token, file, error, massage, statusCode)
        }
    }

    fun loadState(): LiveData<TokenModel> {
        return pref.loadState()
    }
}