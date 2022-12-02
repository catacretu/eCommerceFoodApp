package com.example.ecommercefoodapp.di

import android.content.Context
import com.example.ecommercefoodapp.data.database.AppDatabase
import com.example.ecommercefoodapp.data.local.dao.FoodDAO
import com.example.ecommercefoodapp.data.remote.FoodAPI
import com.example.ecommercefoodapp.data.remote.RemoteDataSource
import com.example.ecommercefoodapp.data.repository.FoodRepository
import com.example.ecommercefoodapp.data.repository.FoodRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    @ViewModelScoped
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getAppDatabase(context)
    }

    @Provides
    @ViewModelScoped
    fun providesFoodDao(appDatabase: AppDatabase): FoodDAO {
        return appDatabase.getFoodDAO()
    }

    @ViewModelScoped
    @Provides
    fun providesGson() = Gson()

    @ViewModelScoped
    @Provides
    fun providesFoodAPI(
        remoteDataSource: RemoteDataSource
    ): FoodAPI {
        return remoteDataSource.buildApi(
            FoodAPI::class.java,
        )
    }

    @ViewModelScoped
    @Provides
    fun providesFoodRepository(foodAPI: FoodAPI, foodDAO: FoodDAO): FoodRepository {
        return FoodRepositoryImpl(foodAPI, foodDAO)
    }
}