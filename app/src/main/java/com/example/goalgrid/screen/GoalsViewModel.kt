package com.example.goalgrid.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalgrid.model.Goals
import com.example.goalgrid.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _goalsList = MutableStateFlow<List<Goals>>(emptyList())
    val goalsList = _goalsList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllGoals().distinctUntilChanged().collect { listOfGoals ->
                if (listOfGoals.isEmpty()) {
                    println("The list is empty")
                } else {
                    _goalsList.value = listOfGoals
                }
            }
        }
    }

    fun addGoals(goal: Goals) {
        Log.d("GoalsViewModel", "Adding goal: $goal")
        viewModelScope.launch {
            repository.addGoal(goal)
        }
    }

    fun updateGoal(goal: Goals) {
        viewModelScope.launch {
            repository.updateGoal(goal)
        }
    }

    fun removeGoals(goal: Goals) {
        viewModelScope.launch {
            repository.deleteGoal(goal)
        }
    }
}