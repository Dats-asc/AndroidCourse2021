package com.example.androidcoursehw

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.content.Context
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import kotlin.random.Random

class MusicService : Service() {

    private var mediaPlayer: MediaPlayer = MediaPlayer()

    private var currentSong: Song? = null

    inner class LocaleBinder : Binder() {

        fun play() = this@MusicService.play()

        fun pause() = this@MusicService.pause()

        fun setSong(song: Song){
            currentSong = song
            this@MusicService.setSong()
        }

        fun stop() = this@MusicService.stop()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? = LocaleBinder()

    private fun play(){
        mediaPlayer.run {
            start()
            setOnCompletionListener {
                stop()
            }
        }
    }

    private fun pause(){
        if (mediaPlayer.isPlaying)
            mediaPlayer.pause()
    }

    private fun setSong(){
        currentSong?.let { song ->
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(applicationContext, song.songSrc)
        }
    }

    private fun playLocaleMusic() {
        currentSong?.let { song ->
            if (mediaPlayer.isPlaying) mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(applicationContext, song.songSrc)
            mediaPlayer.run {
                start()
                setOnCompletionListener {
                    stop()
                }
            }
        }
    }

    private fun stop(){
        mediaPlayer.stop()
    }
}