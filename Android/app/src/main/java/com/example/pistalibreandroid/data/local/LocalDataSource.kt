package com.example.pistalibreandroid.data.local

import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val preferencesDatabase: PreferencesDatabase
) {
    companion object {
        val TOKEN = "token_private"
    }

    fun getToken(): String? {
        return preferencesDatabase.getData(TOKEN)
    }

    fun setToken(value: String) {
        preferencesDatabase.setData(TOKEN, value)
    }
}
