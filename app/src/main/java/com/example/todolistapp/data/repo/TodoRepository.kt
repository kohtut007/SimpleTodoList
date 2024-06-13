package com.example.todolistapp.data.repo

import androidx.lifecycle.LiveData
import com.example.todolistapp.data.Todo
import com.example.todolistapp.data.TodoDao

class TodoRepository(private val todoDao: TodoDao) {

    val allTodos: LiveData<List<Todo>> = todoDao.getAllTodos()

    suspend fun insert(todo: Todo){
        todoDao.insert(todo)
    }

    suspend fun delete(todo: Todo){
        todoDao.delete(todo)
    }

    suspend fun update(todo: Todo){
        todoDao.update(todo.id, todo.name)
    }
}