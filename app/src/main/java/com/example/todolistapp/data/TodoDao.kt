package com.example.todolistapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoItem: TodoItem)

    @Delete
    suspend fun delete(todoItem: TodoItem)

    @Query("SELECT * from name_table order by id ASC")
    fun getAllTodos(): LiveData<List<TodoItem>>

    @Query("UPDATE name_table SET name = :name WHERE id = :id")
    suspend fun update(id: Int?, name: String?)

}