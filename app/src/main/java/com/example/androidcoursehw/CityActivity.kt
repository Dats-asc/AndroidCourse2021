package com.example.androidcoursehw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidcoursehw.databinding.ActivityCityBinding
import com.example.androidcoursehw.databinding.ActivityMainBinding

class CityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCityBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        with(binding){
            val city = findCityById(intent?.extras?.getInt("ID") ?: 0)

            tvTitle.text = city.title
            tvDescription.text = city.description
            ivCityPhoto.setImageResource(city.imageSrc)
        }

    }

    private fun findCityById(id: Int): City {
        CityRepository.cities.forEach {
            if(it.id == id){
                return it
            }
        }
        return CityRepository.cities[0]
    }
}