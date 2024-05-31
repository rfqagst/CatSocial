package com.example.catsocial.data.retrofit.repository

import com.example.catsocial.data.retrofit.ApiServices
import com.example.catsocial.data.retrofit.response.CatResponse
import com.example.catsocial.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val apiServices: ApiServices
) {

    suspend fun fetchCat(): Flow<Resource<List<CatResponse>>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiServices.fetchCats()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

}