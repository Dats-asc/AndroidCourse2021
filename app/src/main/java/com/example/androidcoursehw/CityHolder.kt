package com.example.androidcoursehw

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursehw.databinding.ItemCityBinding

class CityHolder (
    private val binding: ItemCityBinding,
    private val action: (Int) -> Unit
        ) : RecyclerView.ViewHolder(binding.root){

    fun bind(item: City){
        with(binding){
            tvTitle.text = item.title
            tvDescription.text = item.description
            ivCityPhoto.setImageResource(item.imageSrc)
        }
        itemView.setOnClickListener{
            action(item.id)
        }
    }

    companion object{

        fun create(
            parent: ViewGroup,
            action: (Int) -> Unit
        ) = CityHolder(
            ItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), action
        )
    }
}