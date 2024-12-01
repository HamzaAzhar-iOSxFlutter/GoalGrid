package com.example.goalgrid.repository

import android.util.Log
import com.example.goalgrid.model.Goals
import com.example.goalgrid.model.GoalsDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(private val goalsDatabaseDao: GoalsDatabaseDao) {
    suspend fun addGoal(goal: Goals) {
        Log.d("Repository", "Inserting goal: $goal")
        goalsDatabaseDao.insertGoals(goal)
    }

    suspend fun updateGoal(goal: Goals) {
        goalsDatabaseDao.update(goal)
    }

    suspend fun deleteGoal(goal: Goals) {
        goalsDatabaseDao.deleteGoal(goal)
    }

    suspend fun deleteAllGoals() {
        goalsDatabaseDao.deleteAllGoals()
    }

    suspend fun getAllGoals(): Flow<List<Goals>> {
        return goalsDatabaseDao.getGoals().flowOn(Dispatchers.IO).conflate()
    }

}