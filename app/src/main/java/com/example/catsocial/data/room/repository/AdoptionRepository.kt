package com.example.catsocial.data.room.repository

import com.example.catsocial.data.room.dao.CatDao
import com.example.catsocial.data.room.entity.Cat
import com.example.catsocial.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AdoptionRepository @Inject constructor(
    private val catDao: CatDao
) {

    fun getAllAdoptions(): Flow<Resource<List<Cat>>> = flow {
        emit(Resource.Loading())
        try {
            val adoptions = catDao.getAllAdoptions().collect { adoptions ->
                emit(Resource.Success(adoptions))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))

        }
    }

    suspend fun insertAdoption(cat: Cat): Resource<Unit> {
        return try {
            catDao.insertAdoption(cat)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        }

    }

    suspend fun deleteAdoption(cat: Cat): Resource<Unit> {
        return try {
            catDao.deleteAdoption(cat)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }

    suspend fun getAdoptionById(id: Int): Resource<Cat> {
        return try {
            val adoption = catDao.getAdoption(id)
            Resource.Success(adoption)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }
}