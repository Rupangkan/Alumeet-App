package com.example.alumeet.core.data.remote.repositories

import com.example.alumeet.core.data.remote.Resource
import retrofit2.Response
import timber.log.Timber

abstract class BaseRepository {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Resource.success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.e("remoteDataSource $message")
        return Resource.error("Network call has failed for a following reason: $message")
    }

}