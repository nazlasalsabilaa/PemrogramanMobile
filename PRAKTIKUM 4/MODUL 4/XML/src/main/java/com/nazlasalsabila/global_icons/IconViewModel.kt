package com.nazlasalsabila.global_icons

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class IconViewModel(private val username: String) : ViewModel() {

    private val _iconList = MutableStateFlow<List<Icon>>(emptyList())
    val iconList: StateFlow<List<Icon>> = _iconList

    private val _selectedItem = MutableStateFlow<Icon?>(null)
    val selectedItem: StateFlow<Icon?> = _selectedItem

    init {
        loadData()
    }

    private fun loadData() {
        _iconList.value = Icon_Data.list_icons

        _iconList.value.forEach {
            Timber.d("Data masuk ke list: ${it.name}")
        }
    }

    fun selectItem(icon: Icon) {
        _selectedItem.value = icon
        Timber.d("Item dipilih: ${icon.name}")
    }

    fun getUser(): String = username
}