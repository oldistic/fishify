package com.dicoding.fishify.ui.regis.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.fishify.repository.Repository
import kotlinx.coroutines.launch

class SignUpViewModel(private val pref: Repository) : ViewModel() {
    val signUpResponse: LiveData<com.dicoding.fishify.api.ActivityResponse.SignUpResponse> = pref.signUpResponse
    val toastText: MutableLiveData<String?> = pref.toastText
    val showLoading: LiveData<Boolean> = pref.showLoading

    fun registerAccount(username: String, name: String, email: String, password: String) {
        viewModelScope.launch {
            pref.registerAccount(username, name, email, password)
        }
    }
}