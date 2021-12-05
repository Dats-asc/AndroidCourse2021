package com.example.androidcoursehw

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RawRes
import androidx.core.app.NotificationCompat

private const val CHANNEL_ID = "channel_id_1"

class NotificationService(
    context: Context
) {

    companion object {
        const val CHANNEL_ID = "channel_id_1"
    }
    private val manager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun showNotification(context: Context, title: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.notification_channel_title),
                IMPORTANCE_DEFAULT
            ).apply {
                val audioAttributes: AudioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build()
                val sound = context.getSoundUri(R.raw.sams_alarm_sound)
                setSound(sound, audioAttributes)
                description =context.getString(R.string.notification_channel_desc)
                lightColor = Color.BLUE
                vibrationPattern = arrayOf(100L,200L,0,400L).toLongArray()
            }.also{
                manager.createNotificationChannel(it)
            }
        }
        val intent = Intent(context, SecondActivity::class.java).let{
            PendingIntent.getActivities(
                context,
                123,
                arrayOf(it),
                PendingIntent.FLAG_ONE_SHOT
            )
        }

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setShowWhen(false)
            .setAutoCancel(true)
            .setContentText("Wake up!")
            .setContentIntent(intent)
            .setSound(context.getSoundUri(R.raw.sams_alarm_sound))


        manager.notify(1,builder.build())
    }

    private fun Context.getSoundUri(
        @RawRes id:Int

    )=Uri.parse("android.resource://${packageName}/$id")
}