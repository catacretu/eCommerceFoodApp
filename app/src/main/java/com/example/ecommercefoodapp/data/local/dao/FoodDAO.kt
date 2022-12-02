package com.example.ecommercefoodapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommercefoodapp.data.local.model.FoodItemEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface FoodDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveFood(foodItemList: List<FoodItemEntity>)

    @Query("Select * FROM food_table")
    fun getAllFood(): Single<List<FoodItemEntity>>

}