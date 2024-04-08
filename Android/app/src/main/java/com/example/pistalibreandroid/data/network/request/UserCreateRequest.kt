package com.example.pistalibreandroid.data.network.request

data class UserCreateRequest (
    val username: String,
    val fullname: String,
    val email: String,
    val password: String
)