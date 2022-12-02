package com.example.ecommercefoodapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ecommercefoodapp.data.local.dao.FoodDAO
import com.example.ecommercefoodapp.data.local.model.FoodItemEntity

@Database(entities = [FoodItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getFoodDAO(): FoodDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "Database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return INSTANCE!!
        }
    }
}