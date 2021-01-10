package com.reddit.app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.reddit.app.R
import com.reddit.app.domain.model.Post
import com.reddit.app.presentation.viewholder.PostViewHolder
import com.reddit.app.utils.DiffCallback

class PostsAdapter : ListAdapter<Post, PostViewHolder>(
    DiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }
}