package com.example.alumeet.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alumeet.R
import com.example.alumeet.core.base.BaseActivity
import com.example.alumeet.core.data.model.local.Feed
import com.example.alumeet.core.preferences.SharedPreferencesUtil
import com.example.alumeet.databinding.ActivityHomeBinding
import com.example.alumeet.loginregister.LoginActivity
import com.example.alumeet.newsletter.NewsLetterActivity
import com.example.alumeet.users.AllUsersActivity
import com.example.alumeet.utils.extensions.onClick
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding
    val users = listOf(
        Feed("Alice", "Student, CSE", "Hello world!"),
        Feed("Bob", "Student, ETE", "Lorem ipsum dolor sit amet."),
        Feed("Charlie", "Alumni, CSE", "Random message here."),
        Feed("David", "Student, ETE", "Another message."),
        Feed("Emma", "Student, CSE", "Some text goes here."),
        Feed("Frank", "Alumni, ETE", "A message for you."),
        Feed("Grace", "Student, CSE", "Just a random message."),
        Feed("Hannah", "Student, CSE", "This is a message."),
        Feed("Isaac", "Alumni, CSE", "Message of the day."),
        Feed("Jane", "Student, ETE", "This is just a test.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()
        initUIElements()
        setFeedRecyclerView()
    }

    private fun initUIElements() {

    }

    private fun setFeedRecyclerView() {
        val feedAdapter = FeedAdapter(users)
        binding.contentHome.rvFeed.adapter = feedAdapter
        binding.contentHome.rvFeed.layoutManager = LinearLayoutManager(this)
        binding.contentHome.rvFeed.setHasFixedSize(true)
    }

    private fun setClickListeners() {
        binding.navView.setNavigationItemSelectedListener(this)
        binding.contentHome.ivMenu.onClick {
            setNavigationBehavior()
        }
//        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.feed).onClick {
//            HomeActivity.start(this)
//        }
//        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.news_letter).onClick {
//            NewsLetterActivity.start(this)
//        }
//        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.all_users).onClick {
//            HomeActivity.start(this)
//        }
//        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.logout).onClick {
//            SharedPreferencesUtil.clearSharedPreferences()
//            LoginActivity.start(this)
//        }
    }

    private fun setNavigationBehavior() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun getScreenName(): String = HomeActivity::class.java.simpleName

    companion object {
        fun start(context: Context, withFlags: Boolean = false) {
            val intent = Intent(context, HomeActivity::class.java)
            if (withFlags) {
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.feed -> {
//                HomeActivity.start(this)
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.logout -> {
                SharedPreferencesUtil.clearSharedPreferences()
                LoginActivity.start(this)
            }
            R.id.news_letter -> {
                NewsLetterActivity.start(this)
            }
            R.id.all_users -> {
                AllUsersActivity.start(this)
            }
        }
        return true
    }
}