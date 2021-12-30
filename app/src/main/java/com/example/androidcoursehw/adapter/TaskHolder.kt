package com.example.androidcoursehw.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursehw.model.entity.Task
import com.example.androidcoursehw.databinding.ItemTaskBinding

class TaskHolder (
    private val binding: ItemTaskBinding,
    private val action: (Int) -> Unit,
    private val actionDelete: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root){

    fun bind(item: Task){
        with(binding){
            tvTaskName.text = item.name
            tvTaskDescription.text = item.description
            tvTaskDate.text = "Срок: " + item.date
        }
        itemView.setOnClickListener{
            action(item.id)
        }
        binding.ivRemoveTask.setOnClickListener{
            actionDelete(item.id)
        }
    }

    companion object{

        fun create(
            parent: ViewGroup,
            action: (Int) -> Unit,
            actionDelete: (Int) -> Unit
        ) = TaskHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), action, actionDelete
        )
    }
}