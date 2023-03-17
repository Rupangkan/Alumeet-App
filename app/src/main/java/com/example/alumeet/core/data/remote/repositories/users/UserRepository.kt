package com.example.alumeet.core.data.remote.repositories.users

import com.example.alumeet.core.data.remote.Resource
import com.example.alumeet.core.data.remote.api.ApiDataSource
import com.example.alumeet.core.data.remote.repositories.BaseRepository
import com.example.alumeet.core.di.dispatcher.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val dispatcherProvider: DispatcherProvider
) : BaseRepository() {

    suspend fun getUsers(): Flow<Resource<Any>> = flow {
        emit(safeApiCall { apiDataSource.getUsers() })
    }.flowOn(dispatcherProvider.io)
}

