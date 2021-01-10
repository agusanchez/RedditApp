package com.reddit.app.domain.usecase

import com.reddit.app.data.repository.RemotePostToLocalDbRepository
import com.reddit.app.domain.model.Post

class GetPostByIdUseCase(private val redditRepository: RemotePostToLocalDbRepository) {
    suspend fun invoke(postId: String): Post = redditRepository.getPostById(postId)
}