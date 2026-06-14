package com.nazlasalsabila.global_icons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val repository: GlobalIconRepository? = null,
    private val username: String? = null
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(GlobalIconViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                GlobalIconViewModel(repository ?: throw IllegalArgumentException("Repository required")) as T
            }
            modelClass.isAssignableFrom(IconViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                IconViewModel(username ?: throw IllegalArgumentException("Username required")) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}