package com.reddit.app.domain.usecase

import com.reddit.app.data.repository.RemotePostToLocalDbRepository
import com.reddit.app.domain.model.Post

class MarkPostAsReadUseCase(private val redditRepository: RemotePostToLocalDbRepository) {
    suspend fun invoke(id: String):List<Post> = redditRepository.markPostAsRead(id)
}