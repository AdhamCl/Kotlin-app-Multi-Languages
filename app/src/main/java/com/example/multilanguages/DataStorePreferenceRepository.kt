package com.example.multilanguages

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ViewModelScoped
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "LanguageData")
class DataStorePreferenceRepository @Inject constructor(
    @ApplicationContext private val context: Context
){
    private val defaultLanguage = "ar"

    private object PreferenceKeys{
        val PREF_LANGUAGE = stringPreferencesKey(name = "language")
    }
    private val dataStore = context.dataStore
    suspend fun setLanguage(language: String){
        dataStore.edit { it[PreferenceKeys.PREF_LANGUAGE] = language }
    }
    val getLanguage: Flow<String> = dataStore.data
        .catch {
            if(it is IOException) emit(emptyPreferences())
            else {}
        }
        .map{
            val PREF_LANGUAGE = it[PreferenceKeys.PREF_LANGUAGE] ?: defaultLanguage
            PREF_LANGUAGE
        }
}


