package com.example.todolistapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.TodoDatabase
import com.example.todolistapp.data.TodoItem
import com.example.todolistapp.data.repo.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TodoRepository
    val allTodo : LiveData<List<TodoItem>>

    init {
        val dao = TodoDatabase.getDatabase(application).getTodoDao()
        repository = TodoRepository(dao)
        allTodo = repository.allTodos
    }

    fun insertTodo(todoItem: TodoItem) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(todoItem)
    }

    fun updateTodo(todoItem: TodoItem) = viewModelScope.launch(Dispatchers.IO){
        repository.update(todoItem)
    }

    fun deleteTodo(todoItem: TodoItem) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(todoItem)
    }
}