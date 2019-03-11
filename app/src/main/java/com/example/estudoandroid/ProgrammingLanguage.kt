package com.example.estudoandroid

import androidx.annotation.DrawableRes

//data exige val na declaracao
//val faz virar propriedade
data class ProgrammingLanguage(
        @DrawableRes
        val imageResourceId: Int,
        val title: String,
        val year: Int,
        val description: String)
