package com.example.estudoandroid.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {
    @JvmStatic @BindingAdapter("image")
    fun loadImage(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView).load(imageUrl).into(imageView)
    }
}
