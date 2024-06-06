package com.example.catsocial.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class Cat(
    @PrimaryKey
    val id : Int,
    val image : Int,
    val name: String,
    val age: Int,
    val weight : Int,
    val gender : String,
    val description : String,
)
