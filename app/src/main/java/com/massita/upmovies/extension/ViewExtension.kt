package com.massita.upmovies.extension

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.load(imagePath: String, callback: Callback) {
    Picasso.get()
            .load(imagePath)
            .noPlaceholder()
            .into(this, callback)
}

fun View.setPaletteColor(image: Bitmap) {
    Palette.from(image).generate { palette ->
        val bgColor = palette.getDarkMutedColor(ContextCompat.getColor(context, android.R.color.black))
        setBackgroundColor(bgColor)
    }
}