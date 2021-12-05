package com.example.androidcoursehw

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RebootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val mySharedPreferences = context.getSharedPreferences(
            MainActivity.APP_PREFERENCES,
            AppCompatActivity.MODE_PRIVATE
        )
        val pendingIntent= PendingIntent.getBroadcast(context,
            MainActivity.ALARM_REQUEST_CODE,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val time = mySharedPreferences.getLong("time",0)
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent)
        Toast.makeText(context, "Booting Completed", Toast.LENGTH_LONG).show();

    }
}