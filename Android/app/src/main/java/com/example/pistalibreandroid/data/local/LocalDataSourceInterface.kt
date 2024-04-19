package com.example.pistalibreandroid.data.local

interface LocalDataSourceInterface {
    fun getToken(): String?
    fun setToken(value: String)
}