package com.example.alumeet.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alumeet.core.data.model.local.Feed
import com.example.alumeet.databinding.ItemFeedListItemBinding
import com.example.alumeet.databinding.ItemUsersListItemBinding

class AllUsersAdapter(private val feedList: List<Feed>) : RecyclerView.Adapter<AllUsersViewHolder>() {

    private lateinit var binding: ItemUsersListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllUsersViewHolder {
        binding = ItemUsersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllUsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllUsersViewHolder, position: Int) {
        val feed = feedList[position]
        holder.bind(feed)
    }

    override fun getItemCount(): Int = feedList.size
}

class AllUsersViewHolder(private val binding: ItemUsersListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(feed: Feed) {
        binding.mtvName.text = feed.name
        binding.mtvOccupation.text = feed.occupation
    }
}