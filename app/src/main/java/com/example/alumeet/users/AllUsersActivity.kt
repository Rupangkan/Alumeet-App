package com.example.alumeet.users

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alumeet.core.base.BaseActivity
import com.example.alumeet.core.data.model.local.Feed
import com.example.alumeet.databinding.ActivityAllUsersBinding
import com.example.alumeet.utils.extensions.onClick

class AllUsersActivity : BaseActivity() {

    private lateinit var binding: ActivityAllUsersBinding
    private lateinit var users: List<Feed>

    val students = listOf(
        "Student, CSE",
        "Student, ETE",
        "Alumni, CSE",
        "Alumni, ETE"
    )

    val firstNames = listOf(
        "Emma", "Olivia", "Ava", "Isabella", "Sophia",
        "Mia", "Charlotte", "Amelia", "Emily", "Abigail", "Rupangkan"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickListeners()
        mapUsers()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.rvNewsLetter.adapter = AllUsersAdapter(users)
        binding.rvNewsLetter.layoutManager = LinearLayoutManager(this)
        binding.rvNewsLetter.setHasFixedSize(true)
    }

    private fun mapUsers() {
        users = (1..10).map {
            Feed(
                firstNames.get(it),
                students.random(),
                message = ""
            )
        }
    }

    private fun initClickListeners() {
        binding.ivBack.onClick {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun getScreenName(): String = AllUsersActivity::class.java.simpleName

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AllUsersActivity::class.java)
            context.startActivity(intent)
        }
    }
}