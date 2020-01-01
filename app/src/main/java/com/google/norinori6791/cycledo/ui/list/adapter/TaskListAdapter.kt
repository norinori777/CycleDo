package com.google.norinori6791.cycledo.ui.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.norinori6791.cycledo.R
import com.google.norinori6791.cycledo.databinding.ListTaskBinding
import com.google.norinori6791.cycledo.model.data.Task

class TaskListAdapter(context: Context?, private val items: MutableList<Task>) : RecyclerView.Adapter<TaskViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val databinding: ListTaskBinding = DataBindingUtil.inflate(inflater, R.layout.list_task, parent, false)
        return TaskViewHolder(databinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int){
        holder.databinding.item = items[position]
    }
}