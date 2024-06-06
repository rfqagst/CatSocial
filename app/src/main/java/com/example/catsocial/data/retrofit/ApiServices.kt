package com.example.catsocial.data.retrofit

import com.example.catsocial.data.retrofit.response.ResponseCatItem
import com.example.catsocial.data.retrofit.response.ResponseImage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("breeds")
    suspend fun fetchCats(
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 0,
    ): List<ResponseCatItem>

    @GET("images/{id}")
    suspend fun fetchImages(
        @Path("id") id: String,
    ): ResponseImage

    @GET("breeds/search")
    suspend fun searchCatByRace(
        @Query("q") race: String,
        @Query("attach_image") attachImage: Int = 1
    ): List<ResponseCatItem>
}