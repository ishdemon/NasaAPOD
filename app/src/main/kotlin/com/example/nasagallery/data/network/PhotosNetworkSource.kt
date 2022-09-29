package com.example.nasagallery.data.network

import com.example.nasagallery.data.model.PhotoDetails

//Not required for now
interface PhotosNetworkSource {
    suspend fun getPhotos(): List<PhotoDetails>
}