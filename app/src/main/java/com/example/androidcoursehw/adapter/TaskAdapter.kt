package com.example.androidcoursehw.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursehw.model.entity.Task

class TaskAdapter (
    private var tasks: ArrayList<Task>,
    private val action: (Int) -> Unit,
    private val actionDelete: (Int) -> Unit
) : RecyclerView.Adapter<TaskHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskHolder = TaskHolder.create(parent, action, actionDelete)

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    fun updateData(newList: List<Task>){
        val callback: TaskDiffUtils = TaskDiffUtils(tasks, newList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)

        tasks.clear()
        tasks.addAll(newList)
    }
}