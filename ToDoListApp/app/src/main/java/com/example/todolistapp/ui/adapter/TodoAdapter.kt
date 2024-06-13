package com.example.todolistapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Namelistapp.R
import com.example.todolistapp.data.TodoItem

class TodoAdapter(private val context: Context, val listener: TodoItemClickListener):
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    private val nameList = ArrayList<TodoItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = nameList[position]
        holder.name.text = item.name
        holder.itemView.setOnClickListener {
            listener.onItemClicked(nameList[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    fun updateList(todoList: List<TodoItem>){
        nameList.clear()
        nameList.addAll(todoList)
        notifyDataSetChanged()
    }

    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.tv_name)

    }

    interface TodoItemClickListener {
        fun onItemClicked(todoItem: TodoItem)
    }
}