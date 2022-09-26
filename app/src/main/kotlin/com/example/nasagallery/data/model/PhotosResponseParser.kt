package com.example.nasagallery.data.model

import com.google.gson.annotations.SerializedName

data class PhotosResponseParser(
	var photosResponse: List<PhotosParser>?
)

data class PhotosParser(

	@field:SerializedName("date")
	var date: String?,

	@field:SerializedName("copyright")
	var copyright: String?,

	@field:SerializedName("media_type")
	var mediaType: String?,

	@field:SerializedName("hdurl")
	var hdUrl: String?,

	@field:SerializedName("service_version")
	var serviceVersion: String?,

	@field:SerializedName("explanation")
	var explanation: String?,

	@field:SerializedName("title")
	var title: String?,

	@field:SerializedName("url")
	var url: String?
)
