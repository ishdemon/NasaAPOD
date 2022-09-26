package com.example.nasagallery.data.model

data class PhotoDetails(
    val date: String,
    val copyright: String,
    val title: String,
    val info: String,
    val url: String,
    val hdUrl: String
) {
    companion object {

        @JvmStatic
        fun fromParser(parser: PhotosParser): PhotoDetails {
            return with(parser) {
                PhotoDetails(
                    date = date?:"",
                    copyright = copyright?:"",
                    title = title?:"",
                    info = explanation?:"",
                    url = url?:"",
                    hdUrl = hdUrl?:""
                )
            }
        }
    }
}