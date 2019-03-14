package com.example.estudoandroid.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository (
        val id: Long,
        val name: String,
        val fullName: String,
        val description: String,
        val owner: Owner): Parcelable

@Parcelize
data class Owner(val login: String, val id: Long, val avatarUrl: String): Parcelable