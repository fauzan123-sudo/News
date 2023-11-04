package com.example.news.data.network

import retrofit2.http.POST

interface UserApi {

    @POST("auth")
    suspend fun loginUser(

    )
}