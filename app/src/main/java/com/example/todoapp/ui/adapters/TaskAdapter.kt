package com.example.todoapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.SingleTaskBinding
import com.example.todoapp.models.TaskModel

class TaskAdapter(var taskList: MutableList<TaskModel>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class TaskViewHolder(private val binding: SingleTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }

        fun bind(task: TaskModel) {
            binding.task = task
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SingleTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun updateTasks(newTasks: MutableList<TaskModel>) {
        taskList = newTasks
        notifyDataSetChanged()
    }


}