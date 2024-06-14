package com.example.catsocial.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.catsocial.data.room.entity.Cat
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdoption(cat: Cat)

    @Delete
    suspend fun deleteAdoption(cat: Cat)

    @Query("SELECT * FROM cats")
    fun getAllAdoptions(): Flow<List<Cat>>

    @Query("SELECT * FROM cats WHERE id = :id")
    fun getAdoptionById(id: Int): Flow<Cat>

    @Query("SELECT * FROM cats WHERE race LIKE :race")
    fun searchCatsByRaces(race: String): Flow<List<Cat>>
}