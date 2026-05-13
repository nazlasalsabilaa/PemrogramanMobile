package com.nazlasalsabila.global_icons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class IconViewModelFactory(
    private val username: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IconViewModel::class.java)) {
            return IconViewModel(username) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}