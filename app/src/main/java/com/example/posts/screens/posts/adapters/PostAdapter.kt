package com.example.posts.screens.posts.adapters

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.Posts

class PostAdapter : PagedListAdapter<Posts, PostViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostViewHolder.from(parent)

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Posts>() {
            override fun areItemsTheSame(oldItem: Posts, newItem: Posts): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Posts, newItem: Posts): Boolean =
                oldItem == newItem
        }
    }
}