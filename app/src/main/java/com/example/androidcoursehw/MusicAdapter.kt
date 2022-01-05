package com.example.androidcoursehw

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter (
    private val songs: List<Song>,
    private val action: (Int) -> Unit
) : RecyclerView.Adapter<MusicHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MusicHolder = MusicHolder.create(parent, action)

    override fun onBindViewHolder(holder: MusicHolder, position: Int) {
        holder.bind(songs[position])
    }

    override fun getItemCount(): Int = songs.size
}