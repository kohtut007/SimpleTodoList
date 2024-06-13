package com.example.todolistapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Namelistapp.databinding.ActivityUpdateNoteBinding
import com.example.todolistapp.data.TodoItem

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var oldName: TodoItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        catchEvent()
    }

    private fun catchEvent() {
        binding.apply {
            btnSave.setOnClickListener {

            }

            btnDelete.setOnClickListener {
                var intent = Intent()
                intent.putExtra("todo", oldName)
                intent.putExtra("delete_todo", true)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}