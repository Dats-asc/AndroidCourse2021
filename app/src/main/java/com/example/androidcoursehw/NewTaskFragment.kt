package com.example.androidcoursehw

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
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
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest
import kotlin.collections.ArrayList
import androidx.core.content.ContextCompat.getSystemService

import android.location.LocationManager
import androidx.core.content.ContextCompat
import android.location.Location
import android.location.LocationListener
import androidx.core.content.getSystemService
import java.io.IOException

import android.location.Address

import java.util.Locale

import android.location.Geocoder

import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.*


class NewTaskFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var binding: FragmentNewTaskBinding

    private lateinit var db: AppDatabase

    private lateinit var navController: NavController

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var scope = CoroutineScope(Job() + Dispatchers.IO)


    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {

            } else {

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = AppDatabase(requireContext())
        navController = findNavController()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
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

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_DENIED
        ) {
            requestLocationAccess()
        }

        with(binding) {
            btnAddTask.setOnClickListener {
                val name = tiTaskName.text.toString()
                val description = tiTaskDescription.text.toString()
                val date = etDate.text.toString()
                var location = getLastKnownLocation()
                if (!name.isNullOrEmpty() && !date.isNullOrEmpty()) {
                    var tasks: ArrayList<Task> = arrayListOf()
                    scope.launch {
                        tasks.addAll(db.taskDao().getTaks())
                        var idLast = 0
                        tasks.lastOrNull()?.let {
                            idLast = it.id
                        }
                        if (idLast != 0)
                            idLast += 1
                        db.taskDao().addTask(
                            Task(
                                idLast,
                                name,
                                description,
                                date,
                                location?.first,
                                location?.second
                            )
                        )
                    }
                    Log.e("result", location?.first.toString())
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

    private fun requestLocationAccess() {
        locationPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }

    @SuppressLint("MissingPermission")
    fun getLastKnownLocation(): Pair<Double, Double>? {
        var result: Pair<Double, Double>? = null
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    result = Pair(latitude, longitude)
                }
            }
        return result
    }


}