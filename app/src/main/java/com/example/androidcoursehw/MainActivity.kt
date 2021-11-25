package com.example.androidcoursehw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursehw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var controller: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        controller = findController(R.id.nav_host_fragment_container)
        binding.bnvMain.setupWithNavController(controller)
    }
}