package com.nazlasalsabila.global_icons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GlobalIconViewModel(private val repository: GlobalIconRepository) : ViewModel() {

    private val _icons = MutableStateFlow<List<GlobalIcon>>(emptyList())
    val icons: StateFlow<List<GlobalIcon>> = _icons

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            repository.getMovies().collect {
                _icons.value = it
            }
        }
    }
}