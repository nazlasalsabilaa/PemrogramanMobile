package com.nazlasalsabila.global_icons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class IconViewModel(private val username: String) : ViewModel() {

    private val _iconList = MutableStateFlow<List<GlobalIcon>>(emptyList())
    val iconList: StateFlow<List<GlobalIcon>> = _iconList

    private val _selectedItem = MutableStateFlow<GlobalIcon?>(null)
    val selectedItem: StateFlow<GlobalIcon?> = _selectedItem

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            Timber.d("Memuat data dari sumber baru")
        }
    }

    fun selectItem(icon: GlobalIcon) {
        _selectedItem.value = icon
        Timber.d("Item dipilih: ${icon.name}")
    }

    fun getUser(): String = username
}