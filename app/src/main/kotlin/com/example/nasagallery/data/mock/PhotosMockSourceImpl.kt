package com.example.nasagallery.data.mock

import android.content.Context
import com.example.nasagallery.common.getJSONFromAssets
import com.example.nasagallery.data.model.PhotoDetails
import com.example.nasagallery.data.model.PhotosParser
import com.google.gson.Gson
import javax.inject.Inject

class PhotosMockSourceImpl@Inject constructor(
    private val context: Context
) : PhotosMockSource {

    override suspend fun getPhotos(): List<PhotoDetails> {

        val response = Gson().fromJson(
           context.getJSONFromAssets("data.json"),
            Array<PhotosParser>::class.java
        )
        return response?.map { PhotoDetails.fromParser(it) }?: throw IllegalStateException("Photos null")
    }
}