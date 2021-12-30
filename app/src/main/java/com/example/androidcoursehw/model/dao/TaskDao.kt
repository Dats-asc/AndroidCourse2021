package com.example.androidcoursehw.model.dao

import androidx.room.*
import com.example.androidcoursehw.model.entity.Task

@Dao
public interface TaskDao{
    @Query("SELECT * FROM tasks")
    fun getTaks() : List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(task: Task)

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getTaskById(taskId: Int) : Task?

    @Delete
    fun removeTask(task: Task)

    @Query("DELETE FROM tasks WHERE 1 = 1")
    fun clear()

    @Update
    fun updateTask(newTask: Task)
}