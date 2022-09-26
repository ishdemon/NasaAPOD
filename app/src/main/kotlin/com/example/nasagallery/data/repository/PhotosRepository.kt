package com.example.nasagallery.data.repository

import com.example.nasagallery.data.model.PhotoDetails
import com.example.nasagallery.data.model.PhotosResponseParser

interface PhotosRepository {
    suspend fun getPhotos(): List<PhotoDetails>
}