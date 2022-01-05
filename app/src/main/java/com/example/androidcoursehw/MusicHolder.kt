package com.example.androidcoursehw

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursehw.databinding.ItemSongBinding

class MusicHolder (
    private val binding: ItemSongBinding,
    private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root){

    fun bind(item: Song){
        with(binding){
            ivSongCover.setImageResource(item.cover)
            tvSongName.text = item.name
            tvArtist.text = item.artist
        }
        itemView.setOnClickListener{
            action(item.id)
        }
    }

    companion object{

        fun create(
            parent: ViewGroup,
            action: (Int) -> Unit
        ) = MusicHolder(
            ItemSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), action
        )
    }
}