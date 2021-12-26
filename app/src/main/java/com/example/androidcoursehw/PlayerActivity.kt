package com.example.androidcoursehw

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.example.androidcoursehw.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {

    private var isPlaying: Boolean = true

    private lateinit var binding: ActivityPlayerBinding

    private var musicServiceBinder: MusicService.LocaleBinder? = null

    private var currentSong: Song? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            musicServiceBinder = service as? MusicService.LocaleBinder
            init()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            musicServiceBinder = null
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        currentSong = findSongById(intent?.extras?.getInt("ID") ?: -1)

        bindService(
            Intent(this, MusicService::class.java),
            connection,
            AppCompatActivity.BIND_AUTO_CREATE
        )

        with(binding){
            ivPlayPauseBtn.setOnClickListener {
                onPlayPauseButtonClicked()
            }

            ivNextBtn.setOnClickListener {
                onSkipNextButtonClicked()
            }

            ivPreviousBtn.setOnClickListener {
                onSkipPreviousButtonClicked()
            }
        }
    }

    private fun init(){
        currentSong?.let { song ->
            with(binding){
                ivSongCover.setImageResource(song.cover)
                ivPlayPauseBtn.setImageResource(R.drawable.ic_baseline_pause_24)
                tvSongName.text = song.name
                tvArtist.text = song.artist
            }
            musicServiceBinder?.let { binder ->
                binder.setSong(song)
                binder.play()
            }
        }
    }

    private fun onPlayPauseButtonClicked(){
        if (isPlaying){
            binding.ivPlayPauseBtn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            musicServiceBinder?.pause()
            isPlaying = false
        }
        else{
            binding.ivPlayPauseBtn.setImageResource(R.drawable.ic_baseline_pause_24)
            musicServiceBinder?.play()
            isPlaying = true
        }
    }

    private fun onSkipPreviousButtonClicked(){
        currentSong = findSongById(currentSong?.id?.minus(1) ?: 0)
        init()
    }

    private fun onSkipNextButtonClicked(){
        currentSong = findSongById(currentSong?.id?.plus(1) ?: 0)
        init()
    }

    private fun findSongById(id: Int) : Song {
        if (id < 0)
            return MusicRepository.songs.last()
        if (id >= MusicRepository.songs.size)
            return MusicRepository.songs.first()
        MusicRepository.songs.forEach {
            if (it.id == id){
                return it
            }
        }
        return MusicRepository.songs.first()
    }

    override fun onDestroy() {
        super.onDestroy()
        musicServiceBinder?.stop()
    }
}