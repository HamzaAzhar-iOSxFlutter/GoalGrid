package com.example.goalgrid.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalsDatabaseDao {

    @Query("SELECT * from goals_tbl")
    fun getGoals(): Flow<List<Goals>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoals(goal: Goals)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(goal: Goals)

    @Query("DELETE from goals_tbl")
    suspend fun deleteAllGoals()


    @Delete
    suspend fun deleteGoal(goal: Goals)
}
