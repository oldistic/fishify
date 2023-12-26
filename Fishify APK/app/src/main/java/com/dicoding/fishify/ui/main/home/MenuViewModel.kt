package com.dicoding.fishify.ui.main.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.fishify.model.*
import com.dicoding.fishify.repository.Repository
import kotlinx.coroutines.launch

class MenuViewModel(private val pref: Repository) : ViewModel() {

    val toastText get() = pref.toastText
    val showLoading get() = pref.showLoading

    fun getStoriesList(token: String): LiveData<PagingData<StoryResponseItem>> {
        viewModelScope.launch {
            pref.getStoriesList(token)
        }
        return pref.getStoriesList(token).cachedIn(viewModelScope)
    }


    fun loadState(): LiveData<TokenModel> {
        return pref.loadState()
    }
}