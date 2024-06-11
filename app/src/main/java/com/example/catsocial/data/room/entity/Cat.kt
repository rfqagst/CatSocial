package com.example.catsocial.data.room.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class Cat(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val image : ByteArray,
    val name: String,
    val age: String,
    val weight : String,
    val gender : String,
    val race : String,
    val kota : String,
    val latitude: Double,
    val longitude: Double, 
    val description : String,
)
