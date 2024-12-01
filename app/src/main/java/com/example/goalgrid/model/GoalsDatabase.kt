package com.example.goalgrid.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.goalgrid.utilities.UUIDConverter

@Database(entities = [Goals::class], version = 1, exportSchema = false)
abstract class GoalsDatabase: RoomDatabase() {
    abstract fun goalsDao(): GoalsDatabaseDao
}