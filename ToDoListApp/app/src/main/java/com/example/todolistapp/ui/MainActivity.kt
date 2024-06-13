package com.example.todolistapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Namelistapp.databinding.ActivityMainBinding
import com.example.todolistapp.data.TodoDatabase
import com.example.todolistapp.data.TodoItem
import com.example.todolistapp.ui.adapter.TodoAdapter
import com.example.todolistapp.ui.viewmodel.TodoViewModel

class MainActivity : AppCompatActivity(), TodoAdapter.TodoItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: TodoDatabase
    private lateinit var viewModel: TodoViewModel
    lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        catchEvent()

        initUI()
//        viewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
//        ).get(NameViewModel::class.java)
//
//        viewModel.allName.observe(this) { list ->
//            list?.let {
//                adapter.updateList(list)
//            }
//        }
//
//        database = NameDatabase.getDatabase(this)
    }

    private fun catchEvent() {
        binding.apply {
            fabAddTodoNote.setOnClickListener {
                startActivity(Intent(this@MainActivity, AddNoteActivity::class.java))
            }
        }
    }

    private fun initUI() {
        binding.apply {
            rvNameList.setHasFixedSize(true)
            rvNameList.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = TodoAdapter(this@MainActivity, this@MainActivity)
            rvNameList.adapter = adapter

        }

        val getContent =
            this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val todo = result.data?.getSerializableExtra("todo") as? TodoItem
                    if (todo != null) {
                        viewModel.insertTodo(todo)
                    }
                }
            }

        binding.fabAddTodoNote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            getContent.launch(intent)
        }

    }

    private val updateOrDeleteTodo =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val todo = result.data?.getSerializableExtra("todo") as TodoItem
                val isDelete = result.data?.getBooleanExtra("delete_todo", false) as Boolean
                if (todo != null && !isDelete) {
                    viewModel.updateTodo(todo)
                } else if (todo != null && isDelete) {
                    viewModel.deleteTodo(todo)
                }
            }
        }

    override fun onItemClicked(todoItem: TodoItem) {
        val intent = Intent(this@MainActivity, UpdateNoteActivity::class.java)
        intent.putExtra("current_todo", todoItem )
        updateOrDeleteTodo.launch(intent)
    }
}