package com.example.alumeet.core.di.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

abstract class DispatcherProvider {
    abstract val main: CoroutineDispatcher
    abstract val io: CoroutineDispatcher
}