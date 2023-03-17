package com.example.alumeet.core.di

import com.example.alumeet.core.di.dispatcher.DispatcherProvider
import com.example.alumeet.core.di.dispatcher.DispatcherProviderImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDispatchProvider(): DispatcherProvider = DispatcherProviderImp()
}