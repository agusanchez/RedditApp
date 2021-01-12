package com.reddit.app.data.repository

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.provider.MediaStore
import androidx.core.graphics.drawable.toBitmap
import coil.Coil
import coil.api.get

class SavePictureRepository(private val appContext: Context) : PictureRepository {

    companion object {
        const val PICTURE_FOLDER = "Pictures"
    }

    override suspend fun savePictureOnGallery(url: String) {
        val drawable = Coil.get(url)
        savePicture(drawable.toBitmap())
    }

    private fun savePicture(bitmap: Bitmap) {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME,
                    "${"picture"}_${System.currentTimeMillis()}.jpg")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH,
                        "${Environment.DIRECTORY_PICTURES}/${PICTURE_FOLDER}")
            }
        }

        val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val contentResolver = appContext.contentResolver
        val uri = contentResolver.insert(contentUri, contentValues)
        val stream = uri?.let { contentResolver.openOutputStream(it) }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        stream?.close()
    }
}