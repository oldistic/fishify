package com.dicoding.fishify.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.fishify.repository.Injection
import com.dicoding.fishify.repository.Repository
import com.dicoding.fishify.ui.main.fishscan.FishScanViewModel
import com.dicoding.fishify.ui.main.home.MenuViewModel
import com.dicoding.fishify.ui.main.logout.LogoutViewModel
import com.dicoding.fishify.ui.main.maps.MapsViewModel
import com.dicoding.fishify.ui.regis.login.LoginViewModel
import com.dicoding.fishify.ui.regis.signup.SignUpViewModel

class ViewModelFactory(private val pref: Repository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(pref) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(pref) as T
            }
            modelClass.isAssignableFrom(MenuViewModel::class.java) -> {
                MenuViewModel(pref) as T
            }
            modelClass.isAssignableFrom(FishScanViewModel::class.java) -> {
                FishScanViewModel(pref) as T
            }
            modelClass.isAssignableFrom(LogoutViewModel::class.java) -> {
                LogoutViewModel(pref) as T
            }
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
                MapsViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
        }
    }
}