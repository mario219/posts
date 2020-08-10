package com.example.posts.screens.posts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Posts
import com.example.posts.databinding.PostItemBinding
import com.example.posts.screens.posts.all.HomeListFragmentDirections

class PostViewHolder(private val parent: PostItemBinding) : RecyclerView.ViewHolder(parent.root) {

    fun bind(item: Posts?) {
        item?.run {
            parent.post = this
            if (favorite.not()) {
                parent.imageFav.visibility = View.GONE
            }
            if (read) {
                parent.imageUnread.visibility = View.GONE
            }
            parent.executePendingBindings()
            parent.root.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    HomeListFragmentDirections.actionGetPostDetail(id.toString(), userId.toString())
                )
            )
        }
    }

    companion object {
        fun from(parent: ViewGroup): PostViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = PostItemBinding.inflate(layoutInflater, parent, false)
            return PostViewHolder(binding)
        }
    }
}