package com.example.androidcoursehw

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context , intent: Intent) {
        val service = NotificationService(context)
        service.showNotification(context,"Alarm")
    }
}