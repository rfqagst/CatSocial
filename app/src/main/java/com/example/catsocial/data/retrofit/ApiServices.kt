package com.example.catsocial.data.retrofit

import com.example.catsocial.data.retrofit.response.CatResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {


    @GET("images/search")
    suspend fun fetchCats(
        @Query("size") size: String = "med",
        @Query("has_breeds") hasBreeds: Boolean = true,
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 5,
        @Query("include_breeds") includeBreeds: Boolean = true,
    ): List<CatResponse>


}