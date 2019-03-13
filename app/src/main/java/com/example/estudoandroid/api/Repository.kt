package com.example.estudoandroid.api

import com.google.gson.annotations.SerializedName

data class Repository(
        val id: Long?,
        val name: String?,
        @SerializedName("full_name")
        val fullName: String,
        val description: String?,
        val owner: Owner?
)

//entre os parenteses eh o constructor
data class Owner(
        val login: String?,
        val id: Long?,
        @SerializedName("avatar_url")
        val avatarUrl: String?
)
