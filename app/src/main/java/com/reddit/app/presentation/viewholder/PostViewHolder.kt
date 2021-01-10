package com.reddit.app.presentation.viewholder

import android.view.View
import androidx.core.content.res.ResourcesCompat
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

    fun bind(post: Post, postActionListener: PostActionListener) = with(binding) {

        itemPostDismiss.setOnClickListener { postActionListener.onDismissedPost(post.id) }

        itemView.setOnClickListener {
            postActionListener.onPostClicked(post.id)
        }

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

        postState(post.wasRead)
        itemPostDescription.text = post.title
        itemPostTitle.text = post.author
        itemPostComments.text = post.comment.toString() + itemPostComments.resources.getString(R.string.item_post_comments)
        itemPostDate.text = DateTimeUtils.getTimeAgo(itemView.context, post.created ?: 0)
    }

    private fun markUnread() = with(binding)  {
        itemPostIsRead.background = ResourcesCompat.getDrawable(itemView.resources,
                R.drawable.shape_circle, null)
        itemPostTitle.setTextColor(itemView.resources.getColor(R.color.white, null))
        itemPostDescription.setTextColor(itemView.resources.getColor(R.color.white, null))
    }

    private fun markAsRead() = with(binding)  {
        itemPostIsRead.background = ResourcesCompat.getDrawable(itemView.resources,
                R.drawable.shape_red_circle, null)
        itemPostTitle.setTextColor(itemView.resources.getColor(R.color.color_grey_disabled, null))
        itemPostDescription.setTextColor(itemView.resources.getColor(R.color.color_grey_disabled, null))
    }

    private fun postState(wasRead: Boolean) {
        if (wasRead) {
            markAsRead()
        } else {
            markUnread()
        }
    }
}