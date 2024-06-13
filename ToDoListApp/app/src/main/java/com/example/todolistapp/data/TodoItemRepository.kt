package com.example.todolistapp.data

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoItemDao) {

    val allNames: LiveData<List<TodoItem>> = todoDao.getAllNames()

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