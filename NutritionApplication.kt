package com.example.bitfitpart1

import android.app.Application

class NutritionApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}