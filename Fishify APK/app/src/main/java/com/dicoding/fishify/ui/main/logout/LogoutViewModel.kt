package com.dicoding.fishify.ui.main.logout

import androidx.lifecycle.*
import com.dicoding.fishify.repository.Repository
import kotlinx.coroutines.launch

class LogoutViewModel(private val pref: Repository) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }
}