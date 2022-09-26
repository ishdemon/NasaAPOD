package com.example.nasagallery.data.repository

import com.example.nasagallery.data.model.PhotoDetails

interface PhotosRepository {
    suspend fun getPhotos(): List<PhotoDetails>
}