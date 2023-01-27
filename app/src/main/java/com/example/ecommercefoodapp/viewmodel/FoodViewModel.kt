package com.example.ecommercefoodapp.viewmodel

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommercefoodapp.data.local.model.FoodItemEntity
import com.example.ecommercefoodapp.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(private val repository: FoodRepository) : ViewModel() {

    private var foodData: MutableLiveData<List<FoodItemEntity>> = MutableLiveData()
    lateinit var cartItemList: MutableList<FoodItemEntity>

    init {
        loadFoodData()
    }

    fun getFoodObserver(): MutableLiveData<List<FoodItemEntity>> {
        return foodData
    }

    private fun loadFoodData() {
        val list = repository.getFood().blockingGet()
        foodData.postValue(list)
    }

    fun filterCartItemsFromDataList(activity: FragmentActivity) {
        cartItemList = mutableListOf()
        val dataList = foodData.value!!
        val sh = activity.getSharedPreferences("shopping_cart", Context.MODE_PRIVATE)
        val shItemsList = sh.all
        for (item in dataList)
            for (shItem in shItemsList.entries.iterator()) {
                if (item.title == shItem.key) {
                    item.quantity = shItem.value as Int
                    cartItemList.add(item)
                }
            }
    }

    fun computeFinalPrice(): String {
        var totalAmount = 0
        for (item in cartItemList)
        {var price = extractPrice(item.price)
            price = price * item.quantity
            totalAmount+= price}
        return "$totalAmount lei"
    }

    fun extractPrice(price: String): Int {
        return price.substring(0,price.indexOf(" ")).toInt()
    }
}