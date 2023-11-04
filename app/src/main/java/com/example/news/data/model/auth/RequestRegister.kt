package com.example.news.data.model.auth

data class RequestRegister(
    var id: String? = null,
    val name:String,
    val username:String,
    val password:String,
    val role:String
)
