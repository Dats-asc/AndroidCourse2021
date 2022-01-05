package com.example.androidcoursehw

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import java.io.Serializable

data class Song(
    var id: Int,
    var name: String,
    var artist: String,
    @DrawableRes var cover: Int,
    @RawRes var songSrc: Int
) : Serializable