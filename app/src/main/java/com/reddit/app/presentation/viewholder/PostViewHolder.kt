package com.reddit.app.presentation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.reddit.app.R
import com.reddit.app.databinding.ItemPostBinding
import com.reddit.app.domain.model.Post
import com.reddit.app.utils.DateTimeUtils

class PostViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemPostBinding.bind(itemView)

    fun bind(post: Post) = with(binding) {

        Glide.with(itemPostImage.context)
                .load(post.thumbnail)
                .transforms(
                    CenterCrop(),
                        RoundedCorners(
                                itemPostImage.context.resources
                                        .getDimensionPixelOffset(R.dimen.ui_1_25m)
                        )
                )
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(itemPostImage)

        itemPostDescription.text = post.title
        itemPostTitle.text = post.author
        itemPostComments.text = post.comment.toString() + itemPostComments.resources.getString(R.string.item_post_comments)
        itemPostDate.text = DateTimeUtils.getTimeAgo(itemView.context, post.created ?: 0)
    }
}