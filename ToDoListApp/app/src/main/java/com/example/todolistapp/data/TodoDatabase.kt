package com.example.todolistapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(TodoItem::class), version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun getTodoItemDao(): TodoItemDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "Name_table"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

}