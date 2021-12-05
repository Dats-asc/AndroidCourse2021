package com.example.androidcoursehw

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcoursehw.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding.btnOk.setOnClickListener {
            val intent = Intent(this@SecondActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}