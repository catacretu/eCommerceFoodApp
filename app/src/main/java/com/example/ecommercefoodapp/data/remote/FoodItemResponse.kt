package com.example.ecommercefoodapp.data.remote

import com.example.ecommercefoodapp.data.local.model.FoodItemEntity

data class FoodItemResponse(
    val id: Int, val title: String, val price: String, val imageUrl: String
)

fun FoodItemResponse.toFoodItemEntity() = FoodItemEntity(
    foodID = id, title = title, price = price, imageUrl = imageUrl
)
