package com.reddit.app.data.repository

interface PictureRepository {
    suspend fun savePictureOnGallery(url: String)
}