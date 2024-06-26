package com.example.pistalibreandroid.helpers.fake

import com.example.pistalibreandroid.data.local.LocalDataSourceInterface

class FakeLocalDataSource : LocalDataSourceInterface {

    private var sharedPreferences = mutableMapOf<String, String>()
    private val token = "token"
    private val type = "type"

    override fun getToken(): String? {
        return sharedPreferences[token]
    }

    override fun setToken(value: String) {
        sharedPreferences[token] = value
    }

    override fun getTypeUser(): String? {
        return sharedPreferences[type]
    }

    override fun setTypeUser(value: String) {
        sharedPreferences[type] = value
    }

}