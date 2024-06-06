package com.example.catsocial.data.retrofit.response

import com.example.catsocial.data.retrofit.model.CatWithImage
import com.google.gson.annotations.SerializedName

data class ResponseCat(

	@field:SerializedName("ResponseCat")
	val responseCat: List<ResponseCatItem?>? = null
)

data class ResponseCatItem(

	@field:SerializedName("origin")
	val origin: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("country_codes")
	val countryCodes: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("alt_names")
	val altNames: String? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("temperament")
	val temperament: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("reference_image_id")
	val referenceImageId: String? = null,

)

fun ResponseCatItem.toCatWithImage(imageUrl: String? = null): CatWithImage {
	return CatWithImage(
		id = this.id ?: "",
		name = this.name,
		origin = this.origin,
		description = this.description,
		countryCodes = this.countryCodes,
		altNames = this.altNames,
		countryCode = this.countryCode,
		temperament = this.temperament,
		referenceImageId = this.referenceImageId,
		imageUrl = imageUrl
	)
}
