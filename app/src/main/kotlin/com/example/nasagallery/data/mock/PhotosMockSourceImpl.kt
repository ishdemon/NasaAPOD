package com.example.nasagallery.data.mock

import android.content.Context
import com.example.nasagallery.data.model.PhotoDetails
import com.example.nasagallery.data.model.PhotosResponseParser
import com.google.gson.Gson
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject

class PhotosMockSourceImpl@Inject constructor(
    private val context: Context
) : PhotosMockSource {

    override suspend fun getPhotos(): List<PhotoDetails> {

        val response = Gson().fromJson(
           getJSONFromAssets("data.json"),
            PhotosResponseParser::class.java
        )
        return response.photosResponse?.map { PhotoDetails.fromParser(it) } ?: emptyList()
    }

    private fun getJSONFromAssets(name: String): String? {
        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = context.assets.open(name)
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}