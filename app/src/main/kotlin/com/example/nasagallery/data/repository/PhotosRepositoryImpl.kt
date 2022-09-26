package com.example.nasagallery.data.repository

import com.example.nasagallery.data.mock.PhotosMockSource
import com.example.nasagallery.data.model.PhotoDetails
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val photosMockSource: PhotosMockSource
): PhotosRepository {

    override suspend fun getPhotos(): List<PhotoDetails> {
        return photosMockSource.getPhotos()
    }
}