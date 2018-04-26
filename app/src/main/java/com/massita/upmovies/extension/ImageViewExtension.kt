package com.massita.upmovies.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(imagePath: String) {
    Glide.with(this)
            .load(imagePath)
            .into(this)
}