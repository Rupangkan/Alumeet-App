package com.example.alumeet.core.di

import com.example.alumeet.core.data.local.room.dao.UsersDao
import com.example.alumeet.core.data.local.room.repositories.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesUsersRepo(usersDao: UsersDao) = UsersRepository(usersDao)

}