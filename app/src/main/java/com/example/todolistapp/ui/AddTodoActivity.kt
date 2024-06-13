package com.example.todolistapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.todolistapp.data.Todo
import com.example.todolistapp.databinding.ActivityAddTodoBinding

class AddTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTodoBinding
    private lateinit var todo: Todo
    private lateinit var oldTodo: Todo
    private var isUpdate = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        catchEvent()
    }

    private fun initUI() {
        try {
            oldTodo = intent.getSerializableExtra("current_todo") as Todo
            binding.etName.setText(oldTodo.name)
            isUpdate = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (isUpdate) {
            binding.ivDelete.visibility = View.VISIBLE
        } else {
            binding.ivDelete.visibility = View.INVISIBLE
        }
    }

    private fun catchEvent() {
        binding.ivCheck.setOnClickListener {
            val name = binding.etName.text.toString()

            if (name.isNotEmpty()) {

                if (isUpdate) {
                    todo = Todo(oldTodo.id, name)
                } else {
                    todo = Todo(null, name)
                }

                val intent = Intent()
                intent.putExtra("todo", todo)
                setResult(Activity.RESULT_OK, intent)
                finish()

            } else {
                Toast.makeText(this@AddTodoActivity, "Data cannot be empty!", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
        }

        binding.ivDelete.setOnClickListener {
            val intent = Intent()
            intent.putExtra("todo", oldTodo)
            intent.putExtra("delete_todo", true)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        binding.ivBackArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}