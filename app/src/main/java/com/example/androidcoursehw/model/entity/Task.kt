package com.example.androidcoursehw.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "description")
    var description: String?,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "latitude")
    var latitude: Double?,
    @ColumnInfo(name = "longitude")
    var longitude: Double?
)