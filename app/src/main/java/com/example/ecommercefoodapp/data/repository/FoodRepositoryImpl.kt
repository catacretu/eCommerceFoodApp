package com.example.ecommercefoodapp.data.repository

import com.example.ecommercefoodapp.data.local.dao.FoodDAO
import com.example.ecommercefoodapp.data.local.model.FoodItemEntity
import com.example.ecommercefoodapp.data.remote.FoodAPI
import com.example.ecommercefoodapp.data.remote.toFoodItemEntity
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodAPI: FoodAPI, private val foodDAO: FoodDAO
) : FoodRepository {

    override fun getFood(): Single<List<FoodItemEntity>> {
        return foodAPI.getFood().subscribeOn(Schedulers.io()).map { foodResponseList ->
            foodResponseList.map { foodResponseItem -> foodResponseItem.toFoodItemEntity() }
        }.observeOn(Schedulers.io()).doOnSuccess { foodDAO.saveFood(it) }.onErrorResumeNext {
            foodDAO.getAllFood()
        }
    }
}