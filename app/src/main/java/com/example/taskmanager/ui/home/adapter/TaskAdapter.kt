package com.example.taskmanager.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.ui.task.Task



class TaskAdapter(
    private val context: Context,
    private val onLongClick : (Int) -> Unit,
    private val onClick : (Int) -> Unit,
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    private val data = arrayListOf<Task>()

    fun addTask(task: Task){
        data.add(0,task)
        notifyItemChanged(0)

    }

    fun addTasks(list : List<Task>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }


    fun getTask(position: Int): Task {
        return data[position]
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }





    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(task: Task) {
            if (adapterPosition % 2 == 0){
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context,R.color.white))
                binding.tvDescription.setTextColor(ContextCompat.getColor(context,R.color.white))
            } else {
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context,R.color.black))
                binding.tvDescription.setTextColor(ContextCompat.getColor(context,R.color.black))
            }
            binding.tvDescription.text = task.description
            binding.tvTitle.text = task.title
            binding.root.setOnLongClickListener{
                onLongClick(adapterPosition)
                false
            }

            itemView.setOnClickListener{ onClick(adapterPosition) }


        }


    }

}