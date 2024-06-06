package com.example.catsocial.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.catsocial.data.room.entity.Cat
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdoption(cat: Cat)

    @Delete
    suspend fun deleteAdoption(cat: Cat)

    @Query("SELECT * FROM cats")
    fun getAllAdoptions(): Flow<List<Cat>>

    @Query("SELECT * FROM cats WHERE id = :id")
    suspend fun getAdoption(id: Int): Cat

}