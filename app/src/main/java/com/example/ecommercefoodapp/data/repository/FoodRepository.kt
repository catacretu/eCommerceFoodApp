package com.example.ecommercefoodapp.data.repository

import com.example.ecommercefoodapp.data.local.model.FoodItemEntity
import io.reactivex.rxjava3.core.Single

interface FoodRepository {
    fun getFood(): Single<List<FoodItemEntity>>
}