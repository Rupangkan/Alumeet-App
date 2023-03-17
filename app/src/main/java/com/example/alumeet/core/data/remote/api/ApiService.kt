package com.example.alumeet.core.data.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users/all")
    suspend fun getUsers(): Response<Any>
}