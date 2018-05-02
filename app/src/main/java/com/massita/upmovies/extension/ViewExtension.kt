package com.massita.upmovies.extension

import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.view.View
import android.widget.ImageView
import com.massita.upmovies.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

fun ImageView.load(imagePath: String?, func: () -> Unit) {
    Picasso.get()
            .load(imagePath)
            .placeholder(R.drawable.upcoming_movies_placeholder)
            .into(
                    this,
                    object : Callback {
                        override fun onSuccess() {
                            func()
                        }

                        override fun onError(e: Exception?) {

                        }
                    }
            )
}

fun View.setPaletteColor(image: Bitmap) {
    Palette.from(image).generate { palette ->
        val bgColor = palette.getDarkMutedColor(ContextCompat.getColor(context, android.R.color.black))
        setBackgroundColor(bgColor)
    }
}