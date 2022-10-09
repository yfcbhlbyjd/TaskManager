package com.example.taskmanager.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.taskmanager.ui.task.Task

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Insert
    fun delete(task: Task)

    @Query("SELECT * FROM task")
    fun getAllTask(): List<Task>
}