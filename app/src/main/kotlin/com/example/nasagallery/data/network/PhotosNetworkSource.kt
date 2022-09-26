package com.example.nasagallery.data.network

import com.example.nasagallery.data.model.PhotoDetails

interface PhotosNetworkSource {
    suspend fun getPhotos(): List<PhotoDetails>
}