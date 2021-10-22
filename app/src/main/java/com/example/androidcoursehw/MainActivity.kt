package com.example.androidcoursehw

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidcoursehw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editBtn.setOnClickListener{
            if(!binding.etName.isEnabled){
                binding.etName.isEnabled = true
            }
            else{
                binding.etName.isEnabled = false
            }
        }
    }
}