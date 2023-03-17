package com.example.alumeet.core.data.remote.api

import retrofit2.Response
import javax.inject.Inject

class ApiDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getUsers(): Response<Any> = apiService.getUsers()
}