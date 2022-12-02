package com.example.ecommercefoodapp.data.remote

import com.example.ecommercefoodapp.data.local.model.FoodItemEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface FoodAPI {
    @GET("/catacretu/eCommerceData/main/FoodData")
    fun getFood(): Single<List<FoodItemResponse>>
}