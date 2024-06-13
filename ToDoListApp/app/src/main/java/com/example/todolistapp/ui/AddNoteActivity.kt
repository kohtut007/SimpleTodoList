package com.example.todolistapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.Namelistapp.databinding.ActivityAddNoteBinding
import com.example.todolistapp.data.TodoItem
import java.text.SimpleDateFormat

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var todoItem: TodoItem
    private lateinit var oldName: TodoItem
    var isUpdate = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        catchEvent()

        try {
            oldName = intent.getSerializableExtra("current_todo") as TodoItem
            binding.edtName.setText(oldName.name)
            isUpdate = true
        }catch (e: Exception){
            e.printStackTrace()
        }
//        if(isUpdate){
//            binding.imgDelete.visibility = View.VISIBLE
//        }else{
//            binding.imgDelete.visibility = View.INVISIBLE
//        }
//
//        binding.imgCheck.setOnClickListener {
//            val title = binding.etTitle.text.toString()
//            val todoDescription = binding.etNote.text.toString()
//
//            if(title.isNotEmpty() && todoDescription.isNotEmpty()){
//                val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")
//
//                if(isUpdate){
//                    todo = Todo(oldTodo.id, title, todoDescription, formatter.format(Date()))
//                }else{
//                    todo = Todo(null, title, todoDescription, formatter.format(Date()))
//                }
//                var intent = Intent()
//                intent.putExtra("todo", todo)
//                setResult(Activity.RESULT_OK, intent)
//                finish()
//            }else{
//                Toast.makeText(this@AddTodoActivity, "please enter some data", Toast.LENGTH_LONG).show()
//                return@setOnClickListener
//            }
//        }
//
//        binding.imgDelete.setOnClickListener {
//            var intent = Intent()
//            intent.putExtra("todo", oldTodo)
//            intent.putExtra("delete_todo", true)
//            setResult(Activity.RESULT_OK, intent)
//            finish()
//        }
//
//        binding.imgBackArrow.setOnClickListener {
//            onBackPressed()
//        }
    }

    private fun catchEvent() {
        binding.apply {
            fabSave.setOnClickListener {
                val name = binding.edtName.text.toString()

                if(name.isNotEmpty()){
                    val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")

                }else{
                    Toast.makeText(this@AddNoteActivity, "Please Enter Name!", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }
        }
    }
}