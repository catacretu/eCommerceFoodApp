package com.example.ecommercefoodapp.remote

import com.example.ecommercefoodapp.FoodItemModel
import retrofit2.Response
import retrofit2.http.GET

interface FoodAPI {
    @GET("/catacretu/eCommerceData/main/FoodData")
     suspend fun getFood(): Response<ArrayList<FoodItemModel>>
}