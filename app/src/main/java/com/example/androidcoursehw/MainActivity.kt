package com.example.androidcoursehw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursehw.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var controller: NavController
    private lateinit var binding: ActivityMainBinding
    private var cityAdapter: CityAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        cityAdapter = CityAdapter(CityRepository.cities){
            startActivity(
                Intent(
                    this@MainActivity,
                    CityActivity::class.java
                ).apply {
                    putExtra("ID", it)
                }
            )
        }
        findViewById<RecyclerView>(R.id.rv_cities).run {
            adapter = cityAdapter
        }
    }
}