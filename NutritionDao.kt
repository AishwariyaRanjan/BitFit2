package com.example.bitfitpart1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NutritionDao {
    @Query("SELECT * FROM nutrition_table")
    fun getAll(): Flow<List<NutritionEntity>>

    @Insert
    fun insertAll(foods: List<NutritionEntity>)

    @Insert
    fun insert(food: NutritionEntity)

    @Query("DELETE From nutrition_table")
    fun deleteAll()

    @Query("SELECT SUM(CAST(calories AS INT)) FROM nutrition_table")
    fun getSum():Int

    @Query("SELECT AVG(CAST(calories AS INT)) FROM nutrition_table")
    fun getAverage():Int

    @Query("SELECT MIN(CAST(calories AS INT)) FROM nutrition_table")
    fun getMin():Int

    @Query("SELECT MAX(CAST(calories AS INT)) FROM nutrition_table")
    fun getMax():Int
}