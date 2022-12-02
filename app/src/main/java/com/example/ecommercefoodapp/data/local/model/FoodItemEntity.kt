package com.example.ecommercefoodapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
class FoodItemEntity(
    @PrimaryKey(autoGenerate = false)
    val foodID:Int,
    val title: String,
    val price: String,
    val imageUrl:String
    )