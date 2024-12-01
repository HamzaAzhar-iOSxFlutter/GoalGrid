package com.example.goalgrid.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "goals_tbl")
data class Goals(
    @PrimaryKey
    val uuid: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "goal_title")
    val title: String,
    @ColumnInfo(name = "goal_description")
    val description: String,
    @ColumnInfo(name = "goal_date")
    val dateTime: String)
