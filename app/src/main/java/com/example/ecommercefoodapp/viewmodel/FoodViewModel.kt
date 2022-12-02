package com.example.ecommercefoodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommercefoodapp.data.local.model.FoodItemEntity
import com.example.ecommercefoodapp.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(private val repository: FoodRepository)
    : ViewModel() {

        var foodData: MutableLiveData<List<FoodItemEntity>> = MutableLiveData()

    init{
        loadFoodData()
    }

    fun getFoodObserver(): MutableLiveData<List<FoodItemEntity>> {
        return foodData
    }

    private fun loadFoodData() {
        val list = repository.getFood().blockingGet()
        foodData.postValue(list)
    }
}