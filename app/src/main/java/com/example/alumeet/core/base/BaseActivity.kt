package com.example.alumeet.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alumeet.R
import com.example.alumeet.utils.extensions.setSystemBarColor
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSystemBarColor(R.color.backgroundColorPrimary)
        Timber.i("=> onCreate() %s -- TASK ID: $taskId", getScreenName())
    }

    public override fun onDestroy() {
        Timber.i("<= onDestroy() %s  -- TASK ID: $taskId", getScreenName())
        super.onDestroy()
    }

    abstract fun getScreenName(): String
}