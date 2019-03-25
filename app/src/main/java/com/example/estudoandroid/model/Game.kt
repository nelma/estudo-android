package com.example.estudoandroid.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.estudoandroid.BR
import com.example.estudoandroid.utils.bindable

class Game(
        name: String,
        val launghYear: Int,
        val imageUrl: String,
        rating: Double): BaseObservable() {
    val isClassic = launghYear < 1990

    @get:Bindable
    var rating by bindable(rating, BR.rating)

    @get:Bindable
    var name by bindable(name, BR.name)
}