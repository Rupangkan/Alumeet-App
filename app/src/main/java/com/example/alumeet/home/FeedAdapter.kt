package com.example.alumeet.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alumeet.core.data.model.local.Feed
import com.example.alumeet.databinding.ItemFeedListItemBinding

class FeedAdapter(private val feedList: List<Feed>) : RecyclerView.Adapter<FeedViewHolder>() {

    private lateinit var binding: ItemFeedListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        binding = ItemFeedListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val feed = feedList[position]
        holder.bind(feed)
    }

    override fun getItemCount(): Int = feedList.size
}

class FeedViewHolder(private val binding: ItemFeedListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(feed: Feed) {
        binding.mtvName.text = feed.name
        binding.mtvOccupation.text = feed.occupation
        binding.mtvMessage.text = feed.message

    }
}