package com.example.androidcoursehw

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.androidcoursehw.databinding.FragmentNewTaskBinding
import com.example.androidcoursehw.model.AppDatabase
import com.example.androidcoursehw.model.dao.TaskDao
import com.example.androidcoursehw.model.entity.Task
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_new_task.*
import kotlinx.android.synthetic.main.item_task.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class NewTaskFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var binding: FragmentNewTaskBinding

    private lateinit var db: AppDatabase

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = AppDatabase(requireContext())
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentNewTaskBinding.inflate(inflater, container, false)?.let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding) {
            btnAddTask.setOnClickListener {
                var name = tiTaskName.text.toString()
                var description = tiTaskDescription.text.toString()
                var date = etDate.text.toString()
                if (!name.isNullOrEmpty() && !date.isNullOrEmpty()) {
                    var tasks: ArrayList<Task> = arrayListOf()
                    Thread {
                        tasks.addAll(db.taskDao().getTaks())
                        var idLast = 0
                        tasks.lastOrNull()?.let {
                            idLast = it.id
                        }
                        if (idLast != 0)
                            idLast += 1
                        db.taskDao().addTask(Task(idLast, name, description, date))
                    }.start()
                    Thread.sleep(100)
                    navController.popBackStack()
                } else {
                    Snackbar.make(it, "Введите название и дату", Snackbar.LENGTH_LONG)
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
}