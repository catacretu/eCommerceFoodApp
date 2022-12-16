package com.example.ecommercefoodapp.listener

import com.example.ecommercefoodapp.data.local.model.FoodItemEntity

interface ItemClickListener {
    fun onClick(item : FoodItemEntity)
}