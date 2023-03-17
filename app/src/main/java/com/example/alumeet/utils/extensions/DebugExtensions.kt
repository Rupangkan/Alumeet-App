package com.example.alumeet.utils.extensions

import android.util.Log
import timber.log.Timber
import java.util.concurrent.TimeUnit


//inline fun debug(action: () -> Unit) {
//    if (Boolean) action()
//}
//
//inline fun release(action: () -> Unit) {
//    if (!BuildConfig.DEBUG) action()
//}
//
//fun debug() = BuildConfig.DEBUG

inline fun <T> measureExecution(logMessage: String, logLevel: Int = Log.DEBUG, function: () -> T): T {
    val startTime = System.nanoTime()
    return function.invoke().also {
        val difference = System.nanoTime() - startTime
        Timber.log(logLevel, "$logMessage; took ${TimeUnit.NANOSECONDS.toMillis(difference)}ms")
    }
}