package com.example.todolistapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoItem: TodoItem)

    @Delete
    suspend fun delete(todoItem: TodoItem)

    @Query("SELECT * from Name_table order by id ASC")
    fun getAllNames(): LiveData<List<TodoItem>>

    @Query("UPDATE Name_table set name = :name where id = :id")
    suspend fun update(id: Int?, name: String?)
}