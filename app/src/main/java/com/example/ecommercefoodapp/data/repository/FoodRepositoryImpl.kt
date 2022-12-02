package com.example.ecommercefoodapp.data.repository

import com.example.ecommercefoodapp.data.local.model.FoodItemEntity
import com.example.ecommercefoodapp.data.remote.FoodAPI
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    val foodAPI: FoodAPI
): FoodRepository {

    override fun getFood(): Single<List<FoodItemEntity>> {
    return foodAPI.getFood().subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .doOnSuccess {  }
    }
}