package com.example.alumeet.loginregister

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.alumeet.core.base.BaseActivity
import com.example.alumeet.core.preferences.SharedPreferencesUtil
import com.example.alumeet.databinding.ActivityLoginBinding
import com.example.alumeet.home.HomeActivity
import com.example.alumeet.utils.extensions.onClick
import com.example.alumeet.utils.extensions.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginRegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (SharedPreferencesUtil.loggedIn == "true") {
            HomeActivity.start(this, true)
            finish()
        }
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.mbtnLogin.onClick {
            loginClickResponse()
        }
        binding.mtvRegisterPage.onClick {
            RegisterActivity.start(this)
        }
    }

    private fun loginClickResponse() {
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.getUser(email, password, {
                if(it.isNotEmpty()) {
                    HomeActivity.start(this)
                    SharedPreferencesUtil.loggedIn = "true"
                } else {
                    toast("No User found")
                }
            }, {
                toast(it)
            })
        } else {
            toast("Please fill up the required details")
        }
    }

    override fun getScreenName(): String = LoginActivity::class.java.simpleName

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

}