package com.example.androidcoursehw

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toolbar
import androidx.navigation.NavController
import com.example.androidcoursehw.databinding.ActivityMainBinding
import com.example.androidcoursehw.model.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import android.content.Intent

import android.os.PersistableBundle

import android.view.MenuItem

import android.view.Menu
import android.location.LocationManager





class MainActivity : AppCompatActivity() {

    private lateinit var controller: NavController

    private lateinit var binding: ActivityMainBinding

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller = findController(R.id.nav_host_fragment_container)

        db = AppDatabase(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
}