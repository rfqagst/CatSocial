package com.example.catsocial.data.retrofit.repository

import android.util.Log
import com.example.catsocial.data.retrofit.ApiServices
import com.example.catsocial.data.retrofit.model.CatWithImage
import com.example.catsocial.data.retrofit.response.toCatWithImage
import com.example.catsocial.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val apiServices: ApiServices
) {

    suspend fun fetchCat(): Flow<Resource<List<CatWithImage>>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiServices.fetchCats()
            val catWithImage = response.map { catItem ->
                val imageUrl = catItem.referenceImageId?.let { fetchImageUrl(it) }
                catItem.toCatWithImage(imageUrl).also {
                    Log.d("CatRepository", "Mapped CatWithImage: $it")
                }
            }
            Log.d("CatRepository", "fetchCat: $catWithImage")
            emit(Resource.Success(catWithImage))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)


    suspend fun fetchCatById(catId: String): Flow<Resource<CatWithImage>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiServices.fetchCatById(catId)
            val catWithImage = response.let { catItem ->
                val imageUrl = catItem.referenceImageId?.let { fetchImageUrl(it) }
                catItem.toCatWithImage(imageUrl)

            }
            emit(Resource.Success(catWithImage))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun searchCatByRace(race: String): Flow<Resource<List<CatWithImage>>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiServices.searchCatByRace(race)
            val catsWithImages = response.map { catItem ->
                Log.d("CatRepository", "fetchCat: ${catItem.referenceImageId}")
                val imageUrl = catItem.referenceImageId?.let { fetchImageUrl(it) }
                catItem.toCatWithImage(imageUrl)
            }
            emit(Resource.Success(catsWithImages))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)


    private suspend fun fetchImageUrl(imageId: String): String? {
        return try {
            val imageResponse = apiServices.fetchImages(imageId)
            imageResponse.url.also {
                Log.d("CatRepository", "Fetched Image URL: $it")
            }
        } catch (e: Exception) {
            Log.e("CatRepository", "Error fetching image URL", e)
            null
        }
    }


}