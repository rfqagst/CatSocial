package com.example.catsocial.data.retrofit.model

data class CatWithImage(
    val id: String,
    val name: String?,
    val origin: String?,
    val description: String?,
    val countryCodes: String?,
    val altNames: String?,
    val countryCode: String?,
    val temperament: String?,
    val referenceImageId: String?,
    val imageUrl: String?
)