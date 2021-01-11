package com.reddit.app.domain.usecase

import com.reddit.app.data.repository.SavePictureRepository

class SavePictureUseCase(private val savePictureRepository: SavePictureRepository) {
    suspend fun invoke(url: String) = savePictureRepository.savePictureOnGallery(url)
}