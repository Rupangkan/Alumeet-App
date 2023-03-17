package com.example.alumeet.newsletter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alumeet.core.base.BaseActivity
import com.example.alumeet.core.data.model.local.Feed
import com.example.alumeet.databinding.ActivityNewsLetterBinding
import com.example.alumeet.home.FeedAdapter
import com.example.alumeet.utils.extensions.onClick

class NewsLetterActivity : BaseActivity() {
    private lateinit var binding: ActivityNewsLetterBinding
    private lateinit var users: List<Feed>
    val newsList = listOf(
        "The college is launching a new online course on data science.",
        "The annual college festival will be held on April 15th.",
        "The college has been ranked #1 in the state for engineering.",
        "The college is now accepting applications for the MBA program.",
        "The college is partnering with Google to offer a new program on app development.",
        "The college has received a grant to fund research on renewable energy.",
        "The college is hosting a job fair for graduating students next week.",
        "The college has announced a new scholarship program for underprivileged students.",
        "The college's sports team has won the state championship for the third year in a row.",
        "The college has established a new center for innovation and entrepreneurship."
    )

    val students = listOf(
        "Student, CSE",
        "Student, ETE",
        "Alumni, CSE",
        "Alumni, ETE"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsLetterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickListeners()
        mapMessages()
        setRecyclerView()
    }

    private fun initClickListeners() {
        binding.ivBack.onClick {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setRecyclerView() {
        binding.rvNewsLetter.adapter = FeedAdapter(users)
        binding.rvNewsLetter.layoutManager = LinearLayoutManager(this)
        binding.rvNewsLetter.setHasFixedSize(true)
    }

    private fun mapMessages() {
        users = (1..10).map {
            Feed(
                "User $it",
                students.random(),
                newsList.random()
            )
        }
    }

    override fun getScreenName(): String = NewsLetterActivity::class.java.simpleName

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, NewsLetterActivity::class.java)
            context.startActivity(intent)
        }
    }
}