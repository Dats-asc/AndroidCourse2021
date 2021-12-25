package com.example.androidcoursehw

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Music(
    var id: Int,
    var name: String,
    var artist: String,
    var cover: DrawableRes,
    var songSrc: RawRes
)