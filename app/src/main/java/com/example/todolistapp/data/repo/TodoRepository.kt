package com.example.todolistapp.data.repo

import androidx.lifecycle.LiveData
import com.example.todolistapp.data.TodoDao
import com.example.todolistapp.data.TodoItem

class TodoRepository(private val todoDao: TodoDao) {

    val allTodos: LiveData<List<TodoItem>> = todoDao.getAllTodos()

    suspend fun insert(todoItem: TodoItem){
        todoDao.insert(todoItem)
    }

    suspend fun delete(todoItem: TodoItem){
        todoDao.delete(todoItem)
    }

    suspend fun update(todoItem: TodoItem){
        todoDao.update(todoItem.id, todoItem.name)
    }
}