package com.example.androidcoursehw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidcoursehw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        with(binding){
            btnButton.setOnClickListener{
                var sendIntent = Intent().apply {
                    setAction(Intent.ACTION_SEND)
                    putExtra(Intent.EXTRA_TEXT,etEditText.text.toString())
                    setType("text/plain")
                }
                if (sendIntent.resolveActivity(packageManager) != null) {
                    startActivity(sendIntent)
                }
            }
        }
    }


}