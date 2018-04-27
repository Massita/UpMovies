package com.massita.upmovies.extension

import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.view.View
import android.widget.ImageView
import com.massita.upmovies.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.load(imagePath: String, callback: Callback) {
    Picasso.get()
            .load(imagePath)
            .placeholder(R.drawable.upcoming_movies_placeholder)
            .into(this, callback)
}

fun View.setPaletteColor(image: Bitmap) {
    Palette.from(image).generate { palette ->
        val bgColor = palette.getDarkMutedColor(ContextCompat.getColor(context, android.R.color.black))
        setBackgroundColor(bgColor)
    }
}