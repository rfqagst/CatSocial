package com.example.catsocial.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class ResponseImage(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

)
