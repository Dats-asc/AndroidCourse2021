package com.example.androidcoursehw

import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.util.AttributeSet
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.children
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursehw.adapter.TaskAdapter
import com.example.androidcoursehw.databinding.FragmentTasksBinding
import com.example.androidcoursehw.model.AppDatabase
import com.example.androidcoursehw.model.entity.Task
import org.w3c.dom.Text
import java.util.jar.Attributes

public val SONG_ID = "SONG_ID"

class TasksFragment : Fragment() {

    private var taskAdapter: TaskAdapter? = null

    private lateinit var navController: NavController

    private lateinit var binding: FragmentTasksBinding

    private lateinit var db: AppDatabase

    private lateinit var tvFirstMessage: TextView

    private var databaseEmpty: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController()

        db = AppDatabase(requireContext())
        Thread {
            if (db.taskDao().getTaks().isEmpty()) {
                databaseEmpty = true
            }
        }.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentTasksBinding.inflate(inflater, container, false)?.let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvFirstMessage = view.findViewById<TextView>(R.id.tvFirstMessage)

//        if (databaseEmpty){
//            tvFirstMessage = TextView(this.context, null, R.style.FirstMessageStyle).apply {
//                text = "Для начала работы создайте первую цель"
//                setTextAppearance(R.style.FirstMessageStyle)
//            }
//            binding.rootLayout.addView(tvFirstMessage)
//        }

        var tasks: ArrayList<Task> = arrayListOf()
        Thread {
            tasks.addAll(db.taskDao().getTaks())
        }.start()
        Thread.sleep(20)
        taskAdapter = TaskAdapter(
            tasks,
            {
                //openItem(it)
            },
            {
                deleteButtonClickListener(it, tasks)
            }
            )

        getView()?.findViewById<RecyclerView>(R.id.rv_tasks)?.run {
            adapter = taskAdapter
        }

        with(binding) {
            btnAddTask.setOnClickListener {
                navController.navigate(R.id.action_tasksFragment_to_newTaskFragment)
            }

            if(tasks.isEmpty()){
                tvFirstMessage.visibility = View.VISIBLE
            } else{
                tvFirstMessage.visibility = View.GONE
            }
            Log.e("is empty: ", tasks.isEmpty().toString())
        }
    }

    private fun deleteButtonClickListener(itemId: Int, tasks: ArrayList<Task>){
        var newList:ArrayList<Task> = arrayListOf()
        var taskForRemove: Task? = null
        newList.addAll(tasks)
            newList.forEach{ task ->
                if (task.id == itemId){
                    taskForRemove = task
                    return@forEach
                }
            }
        newList.remove(taskForRemove)
        if(newList.isEmpty()){
            binding.tvFirstMessage.visibility = View.VISIBLE
        }
        taskAdapter?.updateData(newList)
        Thread{
            taskForRemove?.let {
                db.taskDao().removeTask(it)
            }
        }.start()
    }
}