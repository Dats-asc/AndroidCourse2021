package com.example.androidcoursehw

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.androidcoursehw.databinding.ActivityMainBinding
import android.content.Intent
import android.os.SystemClock
import android.widget.EditText
import java.util.*


class MainActivity : AppCompatActivity() {
    companion object{
        const val ALARM_REQUEST_CODE = 1000
        const val APP_PREFERENCES = "myAlaramScumwd"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent: Intent = Intent(this, AlarmReceiver::class.java)

        binding.btnSetAlarm.setOnClickListener {
            var hours = binding.etHours.text.toString().toInt()
            var minutes = binding.etMinutes.text.toString().toInt()


            val pIntent = PendingIntent.getBroadcast(
                this,
                1000,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            val alarmTimeAtUTC: Long = System.currentTimeMillis() + 3000
            var calendar = Calendar.getInstance().also {
                it[Calendar.HOUR_OF_DAY] = hours
                it[Calendar.MINUTE] = minutes
                it[Calendar.SECOND] = 0
                it[Calendar.MILLISECOND] = 0
            }

            val time: Long = calendar.timeInMillis

            alarmManager.set(AlarmManager.RTC_WAKEUP, time, pIntent)
        }


    }
}