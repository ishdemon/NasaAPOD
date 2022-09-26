package com.example.nasagallery.data.mock

import com.example.nasagallery.data.model.PhotoDetails

interface PhotosMockSource {
    suspend fun getPhotos(): List<PhotoDetails>
}