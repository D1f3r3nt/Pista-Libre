package com.example.pistalibreandroid.model

sealed class ResponseState

class Idle: ResponseState()

class ResponseLoading: ResponseState()

class ResponseError(val msg: String): ResponseState()

class ResponseOk(val value: Any): ResponseState()