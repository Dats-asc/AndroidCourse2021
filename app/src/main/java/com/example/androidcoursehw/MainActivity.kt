package com.example.androidcoursehw

import Classes.Employee
import Classes.Student
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var student = Student("Rustam", "Hairullin", 19, 11012, 0)
        var employee = Employee("Rustam", "Hairullin", 19, 1111, 10000)

        println("New student - ${student.getInfo()} was created")
        println("New employee - ${employee.getInfo()} was created")
    }
}

