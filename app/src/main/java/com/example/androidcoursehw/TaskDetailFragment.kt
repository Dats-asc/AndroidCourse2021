package com.example.androidcoursehw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidcoursehw.databinding.FragmentTaskDetailBinding
import com.example.androidcoursehw.databinding.FragmentTasksBinding
import com.example.androidcoursehw.model.AppDatabase
import com.example.androidcoursehw.model.entity.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TaskDetailFragment : Fragment() {

    private lateinit var binding: FragmentTaskDetailBinding

    private var taskId: Int = -1

    private lateinit var db: AppDatabase

    private var currentTask: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = AppDatabase(requireContext())

        arguments?.let {
            taskId = it.getInt("TASK_ID")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentTaskDetailBinding.inflate(inflater, container, false)?.let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //var currentTask: Task? = null

        Thread {
            currentTask = db.taskDao().getTaskById(taskId)
        }.start()
        Thread.sleep(10)

        init()

        with(binding) {
            btnEdit.setOnClickListener {
                etTaskName.isEnabled = true
                etTaskDescription.isEnabled = true
                etDate.isEnabled = true
                btnSaveChanges.isEnabled = true
            }

            btnSaveChanges.setOnClickListener {
                val name = etTaskName.text.toString()
                val description = etTaskDescription.text.toString()
                val date = etDate.text.toString()
                if (!name.isNullOrEmpty() && !date.isNullOrEmpty()) {
                    var newTasks = Task(currentTask?.id ?: 0, name, description, date)
                    Thread {
                        db.taskDao().updateTask(newTasks)
                    }.start()
                    Thread.sleep(100)
                    findNavController().popBackStack()
                    Snackbar.make(it, "Сохранено", Snackbar.LENGTH_LONG)
                        .show()
                }
            }

            etDate.setOnClickListener {
                val datePicker =
                    MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select date")
                        .build()
                datePicker.show(parentFragmentManager, "tag")
                datePicker.addOnPositiveButtonClickListener { time ->
                    val formatter = SimpleDateFormat("dd.MM.yyyy")
                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = time
                    etDate.setText(formatter.format(calendar.time))
                }
            }
        }
    }

    private fun init() {
        with(binding) {
            etTaskName.setText(currentTask?.name)
            etTaskDescription.setText(currentTask?.description)
            etDate.setText(currentTask?.date)
        }
    }
}