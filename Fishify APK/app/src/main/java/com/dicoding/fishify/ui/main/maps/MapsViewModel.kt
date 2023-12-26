package com.dicoding.fishify.ui.main.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.fishify.model.TokenModel
import com.dicoding.fishify.repository.Repository
import kotlinx.coroutines.launch

class MapsViewModel(private val pref: Repository) : ViewModel() {
    val getAllStoriesResponse get() = pref.getAllStoriesResponse
    val showLoading get() = pref.showLoading
    val toastText get() = pref.toastText

    fun getStoriesWithLocation(token: String) {
        viewModelScope.launch {
            pref.getStoriesWithLocation(token)
        }
    }

    fun loadState(): LiveData<TokenModel> {
        return pref.loadState()
    }
}