package com.reddit.app.domain.usecase

import com.reddit.app.data.repository.RemotePostToLocalDbRepository
import com.reddit.app.domain.model.Post

class GetPostsUseCase(private val redditRepository: RemotePostToLocalDbRepository) {
    suspend fun invoke(lastVisible: Int): List<Post> = redditRepository.getNextPosts(lastVisible)
}