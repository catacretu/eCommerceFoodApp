package com.example.ecommercefoodapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "food_table")
class FoodItemEntity(
    @PrimaryKey(autoGenerate = false)
    val foodID: Int,
    val title: String,
    val price: String,
    val imageUrl: String,
    var quantity: Int = 0
) : Serializable