package com.example.pistalibreandroid.data.network.request

data class ClubCreateRequest(
    val name: String,
    val location: String,
    val email: String,
    val password: String,
)