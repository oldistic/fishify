package com.dicoding.fishify.ui.regis.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.fishify.repository.Repository
import com.dicoding.fishify.model.TokenModel
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: Repository) : ViewModel() {
    val loginResult: MutableLiveData<com.dicoding.fishify.api.ActivityResponse.LoginResult?> = pref.loginResult
    val loginResponse: LiveData<com.dicoding.fishify.api.ActivityResponse.LoginResponse> = pref.loginResponse
    val showLoading: LiveData<Boolean> = pref.showLoading
    val toastText: MutableLiveData<String?> = pref.toastText

    fun loginAccount(email: String, password: String) {
        viewModelScope.launch {
            pref.loginAccount(email, password)
        }
    }

    fun saveState(token: TokenModel){
        viewModelScope.launch {
            pref.saveState(token)
        }
    }

    fun login() {
        viewModelScope.launch {
            pref.login()
        }
    }
}