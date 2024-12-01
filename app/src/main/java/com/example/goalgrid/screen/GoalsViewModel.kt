package com.example.goalgrid.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.goalgrid.model.Goals

class GoalsViewModel: ViewModel() {

    var goals = mutableStateListOf<Goals>()
     private set

    fun addGoals(goal: Goals) {
        this.goals.add(goal)
    }

    fun removeGoals(goal: Goals) {
        this.goals.remove(goal)
    }

    fun getAllGoals(): List<Goals> {
        return this.goals
    }
}