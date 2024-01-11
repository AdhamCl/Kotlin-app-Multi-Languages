package com.example.multilanguages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LanguageViewModel(
    private val dataStorePreferenceRepository: DataStorePreferenceRepository
) : ViewModel() {


    private val _language = MutableStateFlow("en")
    var language: StateFlow<String> = _language

    init {
        viewModelScope.launch {
            dataStorePreferenceRepository.getLanguage.collect {
                _language.value = it
            }
        }
    }

    suspend fun getLanguage() {
        dataStorePreferenceRepository.getLanguage.collect {
            _language.value = it
        }
    }
    suspend fun saveLanguage(language: String) {
        dataStorePreferenceRepository.setLanguage(language)
    }
}