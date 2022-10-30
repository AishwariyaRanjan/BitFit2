package com.example.bitfitpart1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Defines an entity for nutrition info including calories of food
@Entity(tableName = "nutrition_table")
data class NutritionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "food") val food: String?,
    @ColumnInfo(name = "calories") val calories: String?
)