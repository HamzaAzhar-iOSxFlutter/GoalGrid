package com.example.goalgrid.di

import android.content.Context
import androidx.room.Room
import com.example.goalgrid.model.Goals
import com.example.goalgrid.model.GoalsDatabase
import com.example.goalgrid.model.GoalsDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideGoalsDao(goalsDatabase: GoalsDatabase): GoalsDatabaseDao {
        return goalsDatabase.goalsDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): GoalsDatabase {
        return Room.databaseBuilder(context, GoalsDatabase::class.java, "goals_db").fallbackToDestructiveMigration().build()
    }
}