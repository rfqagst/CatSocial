package com.example.catsocial.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class CatResponse(
	@field:SerializedName("breeds")
	val breeds: List<Breed>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class Breed(
	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("origin")
	val origin: String? = null,

	@field:SerializedName("temperament")
	val temperament: String? = null,

	@field:SerializedName("weight")
	val weight: Weight? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("life_span")
	val lifeSpan: String? = null,

	@field:SerializedName("reference_image_id")
	val referenceImageId: String? = null,
)

data class Weight(
	@field:SerializedName("imperial")
	val imperial: String? = null,

	@field:SerializedName("metric")
	val metric: String? = null
)