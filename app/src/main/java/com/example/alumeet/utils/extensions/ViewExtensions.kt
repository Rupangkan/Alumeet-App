package com.example.alumeet.utils.extensions

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.example.alumeet.R
import com.google.android.material.tabs.TabLayout

fun Activity.setSystemBarColor(color: Int = R.color.colorSurface, isTextBlack: Boolean = isDarkMode().not()) {
    setStatusBarColor(color)
    setNavigationBarColor(color)
    setSystemBarTextColor(isTextBlack)
}

fun Activity.setStatusBarColor(color: Int) {
    window.statusBarColor = ContextCompat.getColor(this, color)
    WindowCompat.setDecorFitsSystemWindows(window, true)
}

fun Activity.setNavigationBarColor(color: Int) {
    window.navigationBarColor = ContextCompat.getColor(this, color)
}

fun Activity.setSystemBarTextColor(isBlack: Boolean) {
    ViewCompat.getWindowInsetsController(this.window.decorView)?.isAppearanceLightStatusBars = isBlack
}

fun Activity.isDarkMode(): Boolean {
    return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> true
        Configuration.UI_MODE_NIGHT_NO -> false
        else -> false
    }
}

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    val toast = Toast.makeText(this, msg, duration)
    toast.show()
}

fun Context.toastLong(msg: String) {
    toast(msg, Toast.LENGTH_LONG)
}

fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resources.getText(resId), duration).show()
}

fun Context.toastLong(@StringRes resId: Int) {
    toast(resId, Toast.LENGTH_LONG)
}

fun View.onClick(listener: () -> Unit) {
    this.setOnClickListener {
        listener()
    }
}

fun View.onLongClick(listener: () -> Unit) {
    this.setOnLongClickListener {
        listener()
        return@setOnLongClickListener true
    }
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.hidden() {
    visibility = View.INVISIBLE
}

fun View.visible(): Boolean {
    return isVisible
}

fun EditText.afterTextChanged(listener: (text: String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            listener(s.toString())
        }
    })
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.focusAndShowKeyboard() {
    requestFocus()
    if (hasWindowFocus()) {
        showKeyboard()
    } else {
        viewTreeObserver.addOnWindowFocusChangeListener(
            object : ViewTreeObserver.OnWindowFocusChangeListener {
                override fun onWindowFocusChanged(hasFocus: Boolean) {
                    if (hasFocus) {
                        this@focusAndShowKeyboard.showKeyboard()
                        viewTreeObserver.removeOnWindowFocusChangeListener(this)
                    }
                }
            })
    }
}

fun ViewPager2.onPageSelected(listener: (Int) -> Unit) {
    this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            listener(position)
        }
    })
}

fun TabLayout.onTabChanged(onTabChangedListener: (TabLayout.Tab) -> Unit) {
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            onTabChangedListener(tab)
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
        }

        override fun onTabReselected(tab: TabLayout.Tab) {
        }

    })
}

fun TextView.animateTextView(initialValue: Int, finalValue: Int) {
    val valueAnimator = ValueAnimator.ofInt(initialValue, finalValue)
    valueAnimator.duration = 700
    valueAnimator.addUpdateListener { valueAnimator1: ValueAnimator ->
        this.text = valueAnimator1.animatedValue.toString()
    }
    valueAnimator.start()
}
