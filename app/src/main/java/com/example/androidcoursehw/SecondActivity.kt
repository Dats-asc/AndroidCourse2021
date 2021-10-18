package com.example.androidcoursehw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidcoursehw.databinding.ActivityMainBinding
import com.example.androidcoursehw.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }


        intent?.also {
            if(it.action == Intent.ACTION_SEND && it.type == "text/plain") {
                handleSendText(it)
            }
        }
    }

    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            binding.tvResult.text = it
        }
    }
}