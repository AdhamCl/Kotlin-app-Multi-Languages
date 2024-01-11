package com.example.multilanguages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DataStoreViewModelFactory(private val dataStorePreferenceRepository: DataStorePreferenceRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LanguageViewModel::class.java)) {
            return LanguageViewModel(dataStorePreferenceRepository) as T
        }
        throw IllegalStateException()
    }
}