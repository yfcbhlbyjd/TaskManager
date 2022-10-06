package com.example.taskmanager.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmanager.task.Task


@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
        abstract fun dao(): TaskDao}