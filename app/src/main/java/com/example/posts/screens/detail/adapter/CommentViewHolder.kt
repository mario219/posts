package com.example.posts.screens.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Comments
import com.example.posts.databinding.CommentItemBinding

class CommentViewHolder(private val parent: CommentItemBinding) : RecyclerView.ViewHolder(parent.root) {

    fun bind(comment: Comments) {
        parent.comment = comment
        parent.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): CommentViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CommentItemBinding.inflate(layoutInflater, parent, false)
            return CommentViewHolder(binding)
        }
    }
}