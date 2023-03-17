package com.example.alumeet.loginregister

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.alumeet.core.base.BaseActivity
import com.example.alumeet.core.data.model.local.User
import com.example.alumeet.databinding.ActivityRegisterBinding
import com.example.alumeet.utils.extensions.onClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: LoginRegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.mbtnRegister.onClick {
            registerClickResponse()
        }
        binding.mtvLoginPage.onClick {
            LoginActivity.start(this)
        }
    }

    private fun registerClickResponse() {
        var firstName = binding.etFirstName.text.toString()
        var lastName = binding.etLastName.text.toString()
        var occupation = binding.etOccupation.text.toString()
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()

        var user = User(
            firstName = firstName,
            occupation = occupation,
            lastName = lastName,
            email = email,
            password = password
        )
        viewModel.insertUser(user)
    }

    override fun getScreenName(): String = RegisterActivity::class.java.simpleName

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }
}