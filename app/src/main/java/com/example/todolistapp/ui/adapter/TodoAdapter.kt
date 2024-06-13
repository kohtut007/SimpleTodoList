package com.example.todolistapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.data.Todo

class TodoAdapter(private val context: Context, private val itemClickListener: TodoClickListener):
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    private val todoList = ArrayList<Todo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoAdapter.TodoViewHolder, position: Int) {
        val item = todoList[position]
        holder.name.text = item.name
        holder.name.isSelected = true
        holder.todoItem_layout.setOnClickListener {
            itemClickListener.onItemClicked(todoList[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateList(newList: List<Todo>){
        todoList.clear()
        todoList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val todoItem_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val name = itemView.findViewById<TextView>(R.id.tvName)
    }

    interface TodoClickListener {
        fun onItemClicked(todo: Todo)
    }
}