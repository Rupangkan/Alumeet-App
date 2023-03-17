package com.example.alumeet.core.data.remote.repositories.auth

import com.example.alumeet.core.data.remote.api.ApiDataSource
import com.example.alumeet.core.data.remote.repositories.BaseRepository
import com.example.alumeet.core.di.dispatcher.DispatcherProvider
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val dispatcherProvider: DispatcherProvider
) : BaseRepository() {

}